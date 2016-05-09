/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;
import java.util.ArrayList;
import javafx.scene.text.Text;

/**
 *
 * @author tiialehikoinen
 */
public class Ruoka_lista {
    Ruoka_annos paaruoka;
    Ruoka_annos kasvisruoka;
    Ruoka_annos jalkkari;
    ArrayList<Ruoka_annos> lista = new ArrayList();

    public Ruoka_lista(Ruoka_annos paaruoka, Ruoka_annos kasvisruoka, Ruoka_annos jalkkari) {
        this.paaruoka = paaruoka;
        this.kasvisruoka = kasvisruoka;
        this.jalkkari = jalkkari;
    }

    public Ruoka_lista() {
    }

    
    public void ruoka(ArrayList lista2, Ruoka_annos paaruoka){
        lista.add(paaruoka);
    }

    
    public String tulostaLista(){
        String tuloste = "Pääruoka: "+paaruoka.toString()+"\nKasvisruoka: "
        +kasvisruoka.toString()+"\nJälkiruoka: "+jalkkari.getJalkkari().toString()+"\n";
        return tuloste;
    }
    
}
