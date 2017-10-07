import java.util.ArrayList;
import java.util.Random;

public class DNA {
    public int populationNumber;
    public double mutationRate;
    public ArrayList<Unit> units;
    public static String target;
    public static int generation;
    public static float currentBestFitness;

    public DNA(String word, int population, double mutationRate){
        this.mutationRate=mutationRate;
        target=word;
        populationNumber=population;
        units= new ArrayList<>();
        generate();
        cycle();
    }


    public void cycle(){
        boolean done = false;
        while(true){
            calculateFitness();
            done = check();
            if(done)break;
            print();
            evolution();
        }
        System.out.println("ENDE");

    }

    public void generate(){
        Random r = new Random();
        for (int nr = 0; nr < populationNumber;nr++){
            Unit unit = new Unit();
            for (int i = 0; i < target.length();i++){
                unit.dna+= (char)(r.nextInt(94)+32);
            }
            units.add(unit);
        }
    }

    public void calculateFitness(){
        for (Unit unit : units){
            int value=0;
            for (int i = 0; i < target.length();i++){
                if(unit.dna.charAt(i)==target.charAt(i)){
                    value++;
                }
            }
            unit.fitness=(float)(value)/target.length();
        }
    }

    public StringBuilder mutation(String child){
        StringBuilder dna = new StringBuilder();
        dna.append(child);
        Random r = new Random();
        if(r.nextDouble() < mutationRate){
            int rng = r.nextInt(target.length());
            dna.deleteCharAt(rng);
            dna.insert(rng, (char) (r.nextInt(94) + 32));
        }
        return dna;
    }

    public boolean check(){
        boolean done=false;
        for(Unit unit : units){
            if(unit.dna.equals(target)){
                System.out.println("WE GOT IT!");
                System.out.println("Genereation: "+generation);
                System.out.println("Mutation: "+mutationRate);
                System.out.println("Unit text: "+unit.dna);
                System.out.println("Unit fitness: "+unit.fitness);
                done =true;
                break;
            }
        }
       return done;

    }

    public Unit crossover(Unit parrentA,Unit parrentB){
        String dna = "";
        Random r = new Random();
        int split = r.nextInt(target.length());
        for (int i =0; i < target.length();i++){
            if(split>=i){
                dna+=parrentA.dna.charAt(i);
            }else{
                dna += parrentB.dna.charAt(i);
            }
        }
        Unit child = new Unit();
        child.dna = dna;
        return child;
    }

    public void evolution() {
        Random r = new Random();
        ArrayList<Unit> matingPool = new ArrayList<>();
        for(Unit unit : units){
            int fit = (int) (unit.fitness * 100);
            for (int i = 0; i <fit;i++){
                matingPool.add(unit);
            }
        }

        units.clear();
        for(int i =0;i<populationNumber;i++){
            int a = r.nextInt(matingPool.size());
            int b = r.nextInt(matingPool.size());

            Unit child = crossover(matingPool.get(a),matingPool.get(b));
            StringBuilder dna = mutation(child.dna);
            child.dna=dna.toString();
            units.add(child);
        }
        generation++;
    }

    public void print() {

        for (int i = 0; i < units.size() / 10; i++) {
            System.out.println("            " + units.get(i).dna + " = " + (int) (units.get(i).fitness * 100) + "%      |     " + " Generation: " + generation);
        }
    }
}
