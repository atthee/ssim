/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author attelahtinen
 */
public class TestController extends Application {

    double paivays = 1.0;
    int ruokailijat;
    int kasvis;
    int jalkiruoka;
    Palaute palaute = new Palaute();
    double rahaMeno;
    Map<Double,Double> fbHistory = new HashMap();
    Tili sodexoTili = new Tili();
    ObservableList<Ruokalaji> paaruuat = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> lisukkeet = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> jalkkarit = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> kasvisruuat = FXCollections.observableArrayList();
    ArrayList<Ruoka_annos> tanaan = new ArrayList();
    ArrayList<Ruoka_annos> huomenna = new ArrayList();

    @Override
    public void start(Stage primaryStage) {
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
        
        Guitesti3 oikea = new Guitesti3();
        Guitesti3 ala = new Guitesti3();
        Guitesti3 yla = new Guitesti3();
        Guitesti3 vasen = new Guitesti3();
        Guitesti3 keski = new Guitesti3();
        
        sodexoTili.Pano(1000.0);
        /*Scene scene = gui.startGui(sodexoTili.getSaldo(),fbHistory.get(paivays), fbHistory.get(paivays),paaruuat,lisukkeet,kasvisruuat,jalkkarit);
        primaryStage.setOnCloseRequest(e -> {
            Guitesti.close(e);
        });
        primaryStage.setTitle("Opiskelijaravintola");
        primaryStage.setScene(scene);
        //primaryStage.setMinWidth(650);
        //primaryStage.setMinHeight(600);
        primaryStage.show();*/
        BorderPane borderpane = new BorderPane();
        borderpane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 70% 70%, #6EBBFF, #DEF0FF)");
        borderpane.setRight(oikea.oikeareuna());
        borderpane.setBottom(ala.alareuna(sodexoTili.getSaldo(),fbHistory.get(paivays), fbHistory.get(paivays)));
        borderpane.setTop(yla.ylareuna());
        borderpane.setLeft(vasen.vasenreuna(paaruuat, lisukkeet, kasvisruuat, jalkkarit));
        borderpane.setCenter(keski.keskusta());
        
        Scene scene = new Scene(borderpane,650,600);
        primaryStage.setTitle("Opiskelijaravintola");
        primaryStage.setScene(scene);
        //primaryStage.setMinWidth(650);
        //primaryStage.setMinHeight(600);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
