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
public class Palaute {
    public int taso(Ruoka_annos annos, Ruoka_annos jalkkari){
        Ruokalaji paaruoka = annos.getPaaruoka();
        Ruokalaji lisuke = annos.getLisuke();
        double kerroin = paaruoka.getSuosio();
        double kerroin2 = lisuke.getSuosio();
        Ruokalaji jalki = jalkkari.getJalkkari();
        double kerroin3 = jalki.getSuosio();
        double yht = (kerroin + kerroin2 + kerroin3);
        if (yht > 10.0){
            return 3;
        }
        else if (yht > 5.0){
            return 2;
        
    }
        return 1;
    }
    
    public String palaute(int taso, int riittava){
        if (riittava == 0){
            return "Ruoka loppui kesken :( miksi ruokaa ei ikinä ole tarpeeksi";
        }
        else{
            switch(taso){
                case 1:
                    return "Ruoka oli pahaa :( miksi ei ole ikinä hyvää ruokaa";
                
                case  2:
                    return "Ihan ok";
                case 3:
                    return "Olipas hyvää ruokaa ja vielä riittävästikin";
                   
            }
        }
        return "Kävin subissa";
    }
    
}

