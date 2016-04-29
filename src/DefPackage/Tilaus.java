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
public class Tilaus {
    
    public Tilaus(){
        
    }
    public double teeTilaus(int ruokailija, int kasvis, int jalkkari, Ruoka_annos paaruoka, Ruoka_annos kasvisruoka, Ruoka_annos jalkiruoka){
        double hinta = (ruokailija*paaruoka.annosHinta(paaruoka))+(kasvis*kasvisruoka.annosHinta(kasvisruoka))+(jalkkari*jalkiruoka.jalkkariHinta(jalkiruoka));
        return hinta;
        
    }
    public String tilausErittely(int ruokailija, int kasvis, int jalkkari, Ruoka_annos paaruoka, Ruoka_annos kasvisruoka, Ruoka_annos jalkiruoka){
        String erittely = ("Pääruuat " + ruokailija + " x " + paaruoka.getPaaruoka() + paaruoka.getLisuke() + ".\nKasvisruuat " + kasvis + " x " + kasvisruoka.getPaaruoka() + kasvisruoka.getLisuke() + ".\nJälkiruokia " + jalkkari + " x " + jalkiruoka.getJalkkari());
        return erittely;
    
    }
    
}
