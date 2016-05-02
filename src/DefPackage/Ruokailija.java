/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

/**
 *
 * @author tiialehikoinen
 */
public class Ruokailija {
    int ruokailija;
    int kasvis;
    int jalkkari;
    
    public Ruokailija(){
    
    }

    public int getRuokailija() {
        return ruokailija;
    }

    public void setRuokailija(int ruokailija) {
        this.ruokailija = ruokailija;
    }
    public void setKasvis(int kasvis){
        this.kasvis = kasvis;
    }

    public int getKasvis() {
        return kasvis;
    }

    public int getJalkkari() {
        return jalkkari;
    }
    
    public void setJalkkari(int jalkkari){
        this.jalkkari = jalkkari;
    }
    
}
