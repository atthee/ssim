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
    
    
    public Ruokalaji(String nimi, double myyntihinta, double ostohinta, double suosio){
        this.nimi = nimi;
        this.myyntihinta = myyntihinta;
        this.ostohinta = ostohinta;
        this.suosio = suosio;
          
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


    @Override
    public String toString() {
        return nimi;
    }
    
        
    
}
