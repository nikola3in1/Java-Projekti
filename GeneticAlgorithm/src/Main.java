public class Main {
    public static void main(String[] args){
        if(args.length<3){
            System.out.println("Invalid input!");
            System.out.println("--> java Main target population mutationRate");
        }else{
            new Main(args[0],Integer.parseInt(args[1]),Double.parseDouble(args[2]));
        }
    }
    public Main(String target, int population , double mutationRate){
        DNA dna = new DNA(target,population,mutationRate);
    }

}
