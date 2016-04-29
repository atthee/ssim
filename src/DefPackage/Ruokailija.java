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
    public int kasvis(double osuus){
        kasvis = (int)(ruokailija*osuus)/100;
        return kasvis;
    }
    public int jalkkari(int myynti){
        jalkkari = myynti;
        return jalkkari;
    }
    
}
