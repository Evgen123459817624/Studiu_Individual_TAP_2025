package org.example.studiu_individual_tap_2025;

import javafx.beans.property.SimpleStringProperty;

public class Indicator {
    private final SimpleStringProperty denumire;
    private final SimpleStringProperty valoare;

    public Indicator(String denumire, String valoare) {
        this.denumire = new SimpleStringProperty(denumire);
        this.valoare = new SimpleStringProperty(valoare);
    }

    public String getDenumire() { return denumire.get(); }
    public String getValoare() { return valoare.get(); }
}
