package sample;

import com.sun.xml.internal.ws.util.StringUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.*;

public class StartStage extends Stage {
    private TableView<Lista> table = new TableView<>();
    private final ObservableList<Lista> data =
            FXCollections.observableArrayList();

    public StartStage() {

        //Input
        BorderPane root = new BorderPane();
        HBox input = new HBox();
        Button racunaj = new Button("Racunaj");
        Button reset = new Button("Reset");
        Text brojGlasova = new Text("Broj glasova:");
        TextField lista = new TextField();
        TextField glasovi = new TextField();
        Text imeListe = new Text("Lista:");
        input.setAlignment(Pos.CENTER);
        input.setPadding(new Insets(15));
        input.setSpacing(15);
        input.getChildren().addAll(imeListe, lista, brojGlasova, glasovi, racunaj,reset);

        //Tabela
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);

        table.setEditable(true);
        TableColumn listaCol = new TableColumn("Lista");
        listaCol.setMinWidth(150);
        listaCol.setCellValueFactory(
                new PropertyValueFactory<>("ime"));
        TableColumn mandatiCol = new TableColumn("Broj mandata");
        mandatiCol.setMinWidth(150);
        mandatiCol.setCellValueFactory(
                new PropertyValueFactory<>("brMandata"));
        mandatiCol.setSortType(TableColumn.SortType.DESCENDING);
        TableColumn glasoviCol = new TableColumn("Broj glasova");
        glasoviCol.setMinWidth(150);
        glasoviCol.setCellValueFactory(
                new PropertyValueFactory<>("brGlasova"));

        table.setMaxSize(450, 520);
        table.getColumns().addAll(listaCol, mandatiCol, glasoviCol);
        table.setItems(data);

        box.getChildren().add(table);
        root.setCenter(box);



        //Dodavanje i racunanje
        racunaj.setOnMouseClicked(mouseEvent -> {
            if(!lista.getText().isEmpty() || !glasovi.getText().isEmpty()){
                Lista noviUnos= validiraj(lista.getText(), glasovi.getText());
                if(noviUnos!=null) {
                    data.add(noviUnos);
                    raucnajMandate();
                    lista.clear();
                    glasovi.clear();
                }
            }else{
                System.out.println("proverite vas unos");
            }
        });

        //Resetovanje
        reset.setOnMouseClicked(mouseEvent -> {
            data.clear();
        });

        root.setTop(input);
        this.setTitle("D'ontoCalc");
        this.setResizable(false);
        this.setScene(new Scene(root, 700, 600));

    }

    private void raucnajMandate() {
        ArrayList<Rezultat> rezultati = new ArrayList<>();
        for (Lista l : data) {
            l.setBrMandata(0);
        }


        for (Lista l : data){
            for (int i = 1; i < 251; i++) {
                rezultati.add(new Rezultat(l.getIme(), Math.ceil((double) l.getBrGlasova() / i)));
            }
        }
        Collections.sort(rezultati);
        for (int i = rezultati.size()-1; i > rezultati.size()-251; i--) {
            String ime = rezultati.get(i).ime;
            for (Lista l : data) {
                if(l.getIme().equals(ime)){
                    l.setBrMandata(l.getBrMandata()+1);
                }
            }
//            System.out.println(rezultati.get(i).ime + " " + rezultati.get(i).vrednost+"  "+i);
        }

    }

    private Lista validiraj(String lista, String brGlasova) {
        int tempGlasovi;
        try {
            tempGlasovi = Integer.parseInt(brGlasova);
        } catch (NumberFormatException e) {
            System.out.println("los unos");
            return null;
        }
        for (Lista l : data) {
            if(l.getIme().equals(lista)){
                System.out.println("vec postoji");
                return null;
            }
        }
        return new Lista(lista,tempGlasovi);
    }
}

class Rezultat implements Comparable<Rezultat> {
    String ime;
    Double vrednost;
    public Rezultat(String ime,Double vrednost) {
        this.ime = ime;
        this.vrednost = vrednost;
    }

    @Override
    public int compareTo(Rezultat rezultat) {
        return vrednost.compareTo(rezultat.vrednost);
    }
}
