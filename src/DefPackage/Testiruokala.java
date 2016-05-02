/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

import java.util.ArrayList;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author tiialehikoinen
 */
public class Testiruokala {
    static Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {
                
        double rahaMeno;

        ObservableList<Ruokalaji> paaruuat = FXCollections.observableArrayList();
        ObservableList<Ruokalaji> lisukkeet = FXCollections.observableArrayList();
        ObservableList<Ruokalaji> jalkkarit = FXCollections.observableArrayList();
        ObservableList<Ruokalaji> kasvisruuat = FXCollections.observableArrayList();
        ArrayList<Ruoka_annos> tanaan = new ArrayList();
        ArrayList<Ruoka_annos> huomenna = new ArrayList();

        Ruokalaji kanaKastike = new Ruokalaji("Kanakastike", 2.0, 1.5, 3.5, 1);
        Ruokalaji riisi = new Ruokalaji("Riisi", 0.6, 0.1, 1.0, 3);
        Ruokalaji ohukaiset = new Ruokalaji("Ohukaiset ja hillo", 0.6, 0.3, 4.2, 4);
        Ruokalaji linssiPata = new Ruokalaji("Linssipata", 1.8, 1.1, 2.4, 2);
        Ruokalaji peruna = new Ruokalaji("Peruna", 0.6, 0.2, 1.2, 3);
        Ruokalaji lihaPulla = new Ruokalaji("Lihapullat", 2.0, 1.5, 3.5, 1);
        Ruokalaji papuKastike = new Ruokalaji("Papukastike", 1.8, 1.0, 2.0, 2);
        Ruokalaji marjaPiirakka = new Ruokalaji("Marjapiirakka", 0.6, 0.2, 3.0, 4);
        
        paaruuat.add(kanaKastike);
        paaruuat.add(lihaPulla);
        lisukkeet.add(riisi);
        lisukkeet.add(peruna);
        jalkkarit.add(ohukaiset);
        jalkkarit.add(marjaPiirakka);
        kasvisruuat.add(linssiPata);
        kasvisruuat.add(papuKastike);

        
        Ruoka_annos annos = new Ruoka_annos();
        Ruoka_annos paaruoka = new Ruoka_annos(kanaKastike, riisi);
        Ruoka_annos kasvisruoka = new Ruoka_annos(linssiPata, peruna);
        Ruoka_annos jalkkari = new Ruoka_annos(ohukaiset);
        
        System.out.println(riisi.getNimi());
        System.out.println(paaruoka.annosHinta(paaruoka));
        System.out.println("ohukaisten myyntihinta on " + ohukaiset.getMyyntihinta());
        System.out.println("Päivän kasvisruuan lisuke on " + kasvisruoka.getLisuke());
       
        lisukkeet = annos.listaus(peruna, riisi);
        paaruuat = annos.listaus(kanaKastike, lihaPulla);
        kasvisruuat = annos.listaus(papuKastike, linssiPata);
        jalkkarit = annos.listaus(ohukaiset, marjaPiirakka);
        for (Ruokalaji alkio : lisukkeet) {
            System.out.println(alkio);
        }
        
        Ruoka_lista maanantai = new Ruoka_lista();
        
        maanantai.ruoka(tanaan, paaruoka);
        maanantai.ruoka(huomenna, paaruoka);
        maanantai.ruoka(tanaan, kasvisruoka);
        maanantai.ruoka(huomenna, kasvisruoka);
      
        maanantai.tulostaLista(tanaan, jalkkari);
        maanantai.tulostaLista(huomenna, jalkkari);
        
        Ruokailija ruokailija = new Ruokailija();
        System.out.println("Paljonko ruokailijoita on tänään?");
        int maara = lukija.nextInt();
        ruokailija.setRuokailija(maara);
        System.out.println("Paljonko lisäksi on näistä % kasvissyöjiä?");
        int kasvikset = lukija.nextInt();
        ruokailija.setKasvis(kasvikset);
        System.out.println("Kuinka moni ottaa tänään jälkkärin?");
        int jalkiruoka = lukija.nextInt();
        ruokailija.setJalkkari(jalkiruoka);
        /*System.out.println("Ruokailijat tänään: " + ruokailijat + ". Kasvissyöjät tänään: " + kasvis + ". Jälkiruokaa menee: " + jalkiruoka);*/
        
        Tilaus tilaus = new Tilaus();
        rahaMeno = tilaus.teeTilaus(ruokailija.getRuokailija(), ruokailija.getKasvis(), ruokailija.getJalkkari(), paaruoka, kasvisruoka, jalkkari);
        System.out.println("Hinta on: " + rahaMeno);
        System.out.println(tilaus.tilausErittely(ruokailija.getRuokailija(), ruokailija.getKasvis(), ruokailija.getJalkkari(), paaruoka, kasvisruoka, jalkkari));
        
        Kysynta mika = new Kysynta();
        double kysynta = mika.kysynta(paaruoka);
        System.out.println(kysynta);
        /*kysynta = mika.kysynta(kasvisruoka);
        System.out.println(kysynta);*/
        int halukkaat = mika.halukkaat(200, kysynta);
        System.out.println(halukkaat);
        int ostajat = mika.ostajat(halukkaat, ruokailija.getRuokailija());
        System.out.println(ostajat);
        double tulos = mika.tulos(paaruoka, ostajat);
        System.out.println(tulos);
        int riittava = mika.riittava(halukkaat, ruokailija.getRuokailija());
        
        Tili sodexoTili = new Tili();
        sodexoTili.Pano(1000.0);
        sodexoTili.Otto(rahaMeno);
        sodexoTili.Pano(tulos);
        System.out.println("Tilin saldo on: " + sodexoTili.getSaldo());
        
        Palaute palaute = new Palaute();
        int taso = palaute.taso(paaruoka, jalkkari);
        System.out.println(taso);
        System.out.println(palaute.palaute(taso, riittava));
        
    }


}
