/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;
import java.util.ArrayList;

/**
 *
 * @author tiialehikoinen
 */
public class Ruoka_lista {
    Ruoka_annos paaruoka;
    Ruoka_annos kasvisruoka;
    Ruoka_annos jalkkari;
    ArrayList<Ruoka_annos> lista2 = new ArrayList();
    
    public void ruoka(ArrayList lista2, Ruoka_annos paaruoka){
        lista2.add(paaruoka);
    }
    public void tulostaLista(ArrayList<Ruoka_annos> lista2, Ruoka_annos jalkkari){
        System.out.println("Tarjolla tänään:");
        for (Ruoka_annos alkio : lista2){
            Ruokalaji laji = alkio.getPaaruoka();
            String nimi = laji.getNimi();
            laji = alkio.getLisuke();
            String nimi2 = laji.getNimi();
            /*laji = alkio.getJalkkari();
            String nimi3 = laji.getNimi();*/
            System.out.println(nimi + ", " + nimi2);
        }
        Ruokalaji laji = jalkkari.getJalkkari();
        String nimi3 = laji.getNimi();
        System.out.println(nimi3);
    }
    
}
