package com.example.moovies;

import javafx.beans.property.SimpleStringProperty;


public class Mysql {
    private SimpleStringProperty nazwa, autor;
    public Mysql(String nazwa, String autor){
        this.nazwa = new SimpleStringProperty(nazwa);
        this.autor = new SimpleStringProperty(autor);
    }

    public String getNazwa() {
        return nazwa.get();
    }

    public SimpleStringProperty nazwaProperty() {
        return nazwa;
    }

    public int setNazwa(String nazwa) {
        this.nazwa.set(nazwa);
        return 0;
    }

    public String getAutor() {
        return autor.get();
    }

    public SimpleStringProperty autorProperty() {
        return autor;
    }

    public int setAutor(String autor) {
        this.autor.set(autor);
        return 0;
    }
}


