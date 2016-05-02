/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tiialehikoinen
 */
public class Ruoka_annos {

    private Ruokalaji paaruoka;
    private Ruokalaji lisuke;
    private Ruokalaji jalkkari;

    public Ruoka_annos() {

    }

    public Ruoka_annos(Ruokalaji jalkkari) {
        this.jalkkari = jalkkari;
    }

    public Ruoka_annos(Ruokalaji paaruoka, Ruokalaji lisuke) {
        this.paaruoka = paaruoka;
        this.lisuke = lisuke;

    }

    public void setPaaruoka(Ruokalaji paaruoka) {
        this.paaruoka = paaruoka;
    }

    public void setLisuke(Ruokalaji lisuke) {
        this.lisuke = lisuke;
    }

    public void setJalkkari(Ruokalaji jalkkari) {
        this.jalkkari = jalkkari;
    }

    public double annosHinta(Ruoka_annos annos) {
        paaruoka = annos.getPaaruoka();
        lisuke = annos.getLisuke();
        double hinta = ((lisuke.getOstohinta()) + (paaruoka.getOstohinta()));
        return hinta;

    }

    public double jalkkariHinta(Ruoka_annos annos) {
        jalkkari = annos.getJalkkari();
        double hinta = jalkkari.getOstohinta();
        return hinta;
    }

    public Ruokalaji getPaaruoka() {
        return paaruoka;
    }

    public Ruokalaji getJalkkari() {
        return jalkkari;
    }

    public Ruokalaji getLisuke() {
        return lisuke;
    }

    @Override
    public String toString() {
        return paaruoka + " " + lisuke + " " + jalkkari;
    }

    public ObservableList listaus(Ruokalaji paaruoka, Ruokalaji lisuke) {
        ObservableList<Ruokalaji> lista = FXCollections.observableArrayList();
        lista.add(paaruoka);
        lista.add(lisuke);

        return lista;
    }
    
    public double annosMyyntiHinta(Ruoka_annos annos){
        paaruoka = annos.getPaaruoka();
        lisuke = annos.getLisuke();
        double hinta = ((lisuke.getMyyntihinta()) + (paaruoka.getMyyntihinta()));
        return hinta;
    }

    public double jalkkariMyyntiHinta(Ruoka_annos annos){
        jalkkari = annos.getJalkkari();
        double hinta = jalkkari.getMyyntihinta();
        return hinta;
    }

}
