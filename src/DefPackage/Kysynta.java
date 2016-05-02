/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;
import java.util.Random;

/**
 *
 * @author tiialehikoinen
 */
public class Kysynta {
    
    public double kysynta(Ruoka_annos annos){
        Random random = new Random();
        Ruokalaji paaruoka = annos.getPaaruoka();
        Ruokalaji lisuke = annos.getLisuke();
        double kerroin = paaruoka.getSuosio();
        double kerroin2 = lisuke.getSuosio();
        double kerroin3 = (random.nextDouble()*5);
        double kysynta = 20*(kerroin*0.7+kerroin2*0.1+kerroin3*0.2);
        return (kysynta/100);
    }
    
    public double jalkkariKysynta(Ruoka_annos annos){
        Random random = new Random();
        Ruokalaji jalkkari = annos.getJalkkari();
        double kerroin = jalkkari.getSuosio();
        double kerroin2 = (random.nextDouble()*5);
        double kysynta = 20*(kerroin*0.5+kerroin2*0.5);
        return (kysynta/100);
    }
    
    public int halukkaat(int opiskelijat, double kysynta){//opiskelijat tarkoittaa yhteensä koulussa päivittäin käyviä oppilaita jotka valitsevat ko annoksen (lihansyöjät, kasvissyöjät)
        int halukkaat = (int)(opiskelijat*kysynta);
        return halukkaat;
    }
    public int ostajat(int halukkaat, int ruokailija){
        
        if (halukkaat > ruokailija){
            halukkaat = ruokailija;
            return halukkaat;
            
        }
        return halukkaat;
    }
    
    public double tulos(Ruoka_annos annos, int ostajat){
        return ostajat*annos.annosMyyntiHinta(annos);
    }
    
    public double jalkkariTulos(Ruoka_annos annos, int ostajat){
        return ostajat*annos.jalkkariMyyntiHinta(annos);
    }
    
    public int riittava(int halukkaat, int ruokailija){
        if (ruokailija >= halukkaat){
            return 1;
        }
        return 0;
    }    
}

