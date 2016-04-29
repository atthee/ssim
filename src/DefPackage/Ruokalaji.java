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
public class Ruokalaji {
    String nimi;
    double myyntihinta;
    double ostohinta;
    double suosio;
    int laji; //1 = pääruoka, 2 = kasvisuoka; 3 = lisuke, 4= jälkkäri//
    
    
    public Ruokalaji(String nimi, double myyntihinta, double ostohinta, double suosio, int laji){
        this.nimi = nimi;
        this.myyntihinta = myyntihinta;
        this.ostohinta = ostohinta;
        this.suosio = suosio;
        this.laji = laji;
          
    }

    public String getNimi() {
        return nimi;
    }

    public double getMyyntihinta() {
        return myyntihinta;
    }

    public double getOstohinta() {
        return ostohinta;
    }

    public double getSuosio() {
        return suosio;
    }

    public int getLaji() {
        return laji;
    }

    @Override
    public String toString() {
        return nimi;
    }
    
        
    
}
