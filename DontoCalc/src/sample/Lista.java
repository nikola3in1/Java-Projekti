package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Lista {
    private SimpleStringProperty ime;
    private SimpleIntegerProperty brGlasova;
    private SimpleIntegerProperty brMandata;


    public Lista() {
    }

    public String getIme() {
        return ime.get();
    }

    public SimpleStringProperty imeProperty() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime.set(ime);
    }

    public int getBrGlasova() {
        return brGlasova.get();
    }

    public SimpleIntegerProperty brGlasovaProperty() {
        return brGlasova;
    }

    public void setBrGlasova(int brGlasova) {
        this.brGlasova.set(brGlasova);
    }

    public int getBrMandata() {
        return brMandata.get();
    }

    public SimpleIntegerProperty brMandataProperty() {
        return brMandata;
    }

    public void setBrMandata(int brMandata) {
        this.brMandata.set(brMandata);
    }

    public Lista(String ime, int brGlasova) {
        this.ime = new SimpleStringProperty(ime);
        this.brGlasova = new SimpleIntegerProperty(brGlasova);
        this.brMandata = new SimpleIntegerProperty(0);
        System.out.println(ime+" "+brGlasova);

    }

}
