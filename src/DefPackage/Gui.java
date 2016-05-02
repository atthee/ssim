/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputControl;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author attelahtinen
 */
public class Gui extends Application {
    double paivays = 1;
    boolean erMesVis = false;
    Ruokailija ruokailijat = new Ruokailija();
    Ruoka_annos mainCourse = new Ruoka_annos();
    Ruoka_annos veggieCourse = new Ruoka_annos();
    Ruoka_annos dessertCourse = new Ruoka_annos();
    Palaute palaute = new Palaute();
    Kysynta mika = new Kysynta();
    TextArea resText = new TextArea("");
    ObservableList<Ruokalaji> paaruuat = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> lisukkeet = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> jalkkarit = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> kasvisruuat = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
        // Oliot ja muuttujat
        Map<Double,Double> fbHistory = new HashMap();
        double rahaMeno;
        Tili sodexoTili = new Tili();
        sodexoTili.Pano(1000.0);
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
        
        //  ENSIMMÄISEN NÄKYMÄN RYHMITTYMÄ
        BorderPane borderpane = new BorderPane();
        borderpane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 70% 70%, #6EBBFF, #DEF0FF)");
        Scene orderScene = new Scene(borderpane, 650, 600);
        
        //  TOISEN NÄKYMÄN RYHMITTYMÄ
        BorderPane resultBorder = new BorderPane();
        resultBorder.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 70% 70%, #38A2FF, #DEF0FF)");
        Scene resultScene = new Scene(resultBorder,650,600);

        
        // Tekstialue vasen
        AnchorPane textBox = new AnchorPane();
        textBox.setPadding(new Insets(10, 10, 10, 10));
        textBox.setPrefSize(300.0, 300.0);
        TextArea textA = new TextArea("Welcome!\n");
        textBox.getChildren().add(textA);
        AnchorPane.setRightAnchor(textA, 10.0);
        AnchorPane.setTopAnchor(textA, 15.0);
        borderpane.setRight(textBox);
        textA.setPrefSize(230, 300);
        textA.setMaxSize(230, 400);
        
        //  Alareuna
        Buttons buttons = new Buttons();
        Label tyytyvaisyys = new Label ("Tyytyväisyys");
        Label fbToday = new Label("Tänään: "+fbHistory.get(paivays));
        Label fbWeek = new Label ("Viimeisin viikko: #muokkaa");
        VBox feedbacks = new VBox();
        feedbacks.getChildren().addAll(tyytyvaisyys,fbToday,fbWeek);
        
        VBox saldoBox = new VBox();
        Label saldoText = new Label ("Saldo:");
        Label saldoNum = new Label (""+sodexoTili.getSaldo());
        saldoBox.getChildren().addAll(saldoText,saldoNum);
        saldoBox.setAlignment(Pos.CENTER_LEFT);
        
        BorderPane footer = new BorderPane();
        footer.setStyle("-fx-background-color: #B0DAFF;");
        footer.setPadding(new Insets(10, 10, 10, 10));
        footer.setLeft(feedbacks);
        footer.setCenter(buttons.modButtons(textA));
        footer.setRight(saldoBox);
        borderpane.setBottom(footer);
        
        //  yläreunan logo ja teksti
        StackPane header = new StackPane();
        header.setPadding(new Insets(15, 15, 15, 15));
        ImageView logo = new ImageView(new Image("file:sorekso.png"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(350);
        Label label1 = new Label("Simulator 1.0");
        label1.setTextFill(Color.web("#2A3C93"));
        label1.setFont(Font.font("Cambria", FontWeight.SEMI_BOLD, 18));
        header.getChildren().addAll(logo,label1);
        StackPane.setAlignment(label1, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(logo, Pos.TOP_CENTER);
        borderpane.setTop(header);
        
        // VALINNAT
        
        //Choice boxes (dropdownit)
        VBox selectors = new VBox();
        //selectors.setPrefWidth(180);
        ChoiceBox paaruoka = new ChoiceBox (FXCollections.observableArrayList (paaruuat));
        paaruoka.setPrefWidth(100);
        paaruoka.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelect, newSelect) ->{
            mainCourse.setPaaruoka(paaruuat.get(newSelect.intValue()));
            textA.appendText("Valinta: "+ mainCourse.getPaaruoka().toString()+"\n");
            });
        ChoiceBox lisuke1 = new ChoiceBox(FXCollections.observableArrayList (lisukkeet));
        lisuke1.setPrefWidth(100);
        lisuke1.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelect, newSelect) ->{
            mainCourse.setLisuke(lisukkeet.get(newSelect.intValue()));
            textA.appendText("Valinta: "+ mainCourse.getLisuke().toString()+"\n");
            });
        ChoiceBox kasvisruoka = new ChoiceBox(FXCollections.observableArrayList (kasvisruuat));
        kasvisruoka.setPrefWidth(100);
        kasvisruoka.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelect, newSelect) ->{
            veggieCourse.setPaaruoka(kasvisruuat.get(newSelect.intValue()));
            textA.appendText("Valinta: "+ veggieCourse.getPaaruoka().toString()+"\n");
            });
        ChoiceBox lisuke2 = new ChoiceBox(FXCollections.observableArrayList (lisukkeet));
        lisuke2.setPrefWidth(100);
        lisuke2.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelect, newSelect) ->{
            veggieCourse.setLisuke(lisukkeet.get(newSelect.intValue()));
            textA.appendText("Valinta: "+ veggieCourse.getLisuke().toString()+"\n");
            });
        ChoiceBox jalkkari = new ChoiceBox(FXCollections.observableArrayList (jalkkarit));
        jalkkari.setPrefWidth(100);
        jalkkari.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelect, newSelect) ->{
            dessertCourse.setJalkkari(jalkkarit.get(newSelect.intValue()));
            textA.appendText("Valinta: "+ dessertCourse.getJalkkari().toString()+"\n");
            });
        
        ArrayList <ChoiceBox> boxes = new ArrayList();
        boxes.add(paaruoka);
        boxes.add(lisuke1);
        boxes.add(kasvisruoka);
        boxes.add(lisuke2);
        boxes.add(jalkkari);
        
        selectors.getChildren().addAll(paaruoka,lisuke1,kasvisruoka,lisuke2,jalkkari);
        selectors.setPadding(new Insets(20,20,20,20));
        selectors.setSpacing(10);
        selectors.setAlignment(Pos.CENTER_RIGHT);
        
        VBox query = new VBox();
        query.setPadding(new Insets(20,20,20,20));
        query.getChildren().addAll(createFields(textA),selectors);
        query.setSpacing(20);
        borderpane.setLeft(query);
        
        // ----------- KESKIOSA -------------------------
        
        // Tee tilaus -painike
        VBox middle = new VBox();
        Label errorMes = new Label("This is text");
        errorMes.setTextFill(Color.RED);
        errorMes.setVisible(erMesVis);
        Button makeOrder = new Button();
        makeOrder.setDisable(true);
        makeOrder.setText("Tee tilaus");
                
        makeOrder.setOnAction((ActionEvent e) -> {
            textA.appendText("Tilaus päivälle "+Math.round(paivays)+" tehty!\n");
            paivays++;
            erMesVis = !erMesVis;
            errorMes.setVisible(erMesVis);
            primaryStage.setScene(resultScene);
            resText.appendText(calcFeedback()+"\n");
            //resText.setText(calcFeedback());
        });
        
        checkValues(makeOrder, boxes);
        

        middle.getChildren().addAll(errorMes,makeOrder);
        middle.setSpacing(50);
        middle.setAlignment(Pos.CENTER);
        borderpane.setCenter(middle);
        
        primaryStage.setTitle("Opiskelijaravintola");
        primaryStage.setScene(orderScene);
        primaryStage.setMinWidth(650);
        primaryStage.setMinHeight(600);
        /*primaryStage.setOnCloseRequest(e -> {
            close(e);
        });*/
        primaryStage.show();
        
        //--------------------------------------------
        //--------------------------------------------
        //------------- TOINEN NÄKYMÄ ----------------
        //--------------------------------------------
        //--------------------------------------------
        
        StackPane resStack = new StackPane();
        VBox resMid = new VBox();
        Button backToOrder = new Button();
        //TextFlow resultField = new TextFlow(resText);
        
        backToOrder.setText("Seuraava päivä >>");
        backToOrder.setOnAction((ActionEvent e) -> {
            textA.appendText("Valitse päivän "+Math.round(paivays)+" ruokalista!\n");
            paaruoka.getSelectionModel().clearSelection();
            lisuke1.getSelectionModel().clearSelection();
            kasvisruoka.getSelectionModel().clearSelection();
            lisuke2.getSelectionModel().clearSelection();
            jalkkari.getSelectionModel().clearSelection();
            errorMes.setVisible(false);
            makeOrder.setDisable(true);
            primaryStage.setScene(orderScene);
        });
        Rectangle rect = new Rectangle(300,300);
        rect.setFill(Color.web("#DEF0FF"));
        resStack.getChildren().addAll(rect, resText);
        resStack.setAlignment(Pos.CENTER);
        resMid.getChildren().addAll(resStack, backToOrder);
        resMid.setSpacing(30);
        resMid.setAlignment(Pos.CENTER);
        
        // kakkosnäkymän header
        StackPane header2 = new StackPane();
        header2.setPadding(new Insets(15, 15, 15, 15));
        ImageView logo2 = new ImageView(new Image("file:sorekso.png"));
        logo2.setPreserveRatio(true);
        logo2.setFitWidth(350);
        Label label2 = new Label("Simulator 1.0");
        label2.setTextFill(Color.web("#2A3C93"));
        label2.setFont(Font.font("Cambria", FontWeight.SEMI_BOLD, 18));
        header2.getChildren().addAll(logo2,label2);
        StackPane.setAlignment(label2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(logo2, Pos.TOP_CENTER);
        
        Label tyytyvaisyys2 = new Label ("Tyytyväisyys");
        Label fbToday2 = new Label("Tänään: "+fbHistory.get(paivays));
        Label fbWeek2 = new Label ("Viimeisin viikko: #muokkaa");
        VBox feedbacks2 = new VBox();
        feedbacks2.getChildren().addAll(tyytyvaisyys2,fbToday2,fbWeek2);
        
        VBox saldoBox2 = new VBox();
        Label saldoText2 = new Label ("Saldo:");
        Label saldoNum2 = new Label (""+sodexoTili.getSaldo());
        saldoBox2.getChildren().addAll(saldoText2,saldoNum2);
        saldoBox2.setAlignment(Pos.CENTER_LEFT);
        
        BorderPane footer2 = new BorderPane();
        footer2.setStyle("-fx-background-color: #B0DAFF;");
        footer2.setPadding(new Insets(10, 10, 10, 10));
        footer2.setLeft(feedbacks2);
        footer2.setRight(saldoBox2);
        
        resultBorder.setBottom(footer2);
        resultBorder.setTop(header2);
        resultBorder.setCenter(resMid);
        
    }
    
    //--------------------------------------------
    //--------------------------------------------
    //--------------------------------------------
    //--------------------------------------------
    
    public GridPane createFields(TextArea textA) { // luodaan tekstikentät
        GridPane grid = new GridPane();
        
        Label label1 = new Label("Ruokalijat: ");
        label1.setFont(Font.font("Cambria", 16));
        Label label2 = new Label("Kasvis: ");
        label2.setFont(Font.font("Cambria", 16));
        Label label3 = new Label("Jälkiruoka: ");
        label3.setFont(Font.font("Cambria", 16));
        TextField estCust = new TextField();
        TextField veggie = new TextField();
        TextField dessert = new TextField();
        ArrayList <TextField> lukumaarat = new ArrayList();
        lukumaarat.add(estCust);
        lukumaarat.add(veggie);
        lukumaarat.add(dessert);
        
        estCust.setOnKeyPressed((KeyEvent event) -> {
            if (checkTextFields(lukumaarat)) {
                ruokailijat.setRuokailija(Integer.parseInt(estCust.getText()));
            }
        });
        estCust.setAlignment(Pos.CENTER);
        estCust.setPrefSize(60, 20);
        
        veggie.setOnKeyPressed((KeyEvent event) -> {
            if (checkTextFields(lukumaarat)) {
                ruokailijat.setKasvis(Integer.parseInt(veggie.getText()));
            }
        });
        veggie.setAlignment(Pos.CENTER);
        veggie.setPrefSize(60, 20);
        
        dessert.setOnKeyPressed((KeyEvent event) -> {
            if (checkTextFields(lukumaarat)) {
                ruokailijat.setJalkkari(Integer.parseInt(dessert.getText()));
            }
        });
        dessert.setAlignment(Pos.CENTER);
        dessert.setPrefSize(60, 20);
                
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(label1, 0, 1);
        grid.add(label2, 0, 2);
        grid.add(label3, 0, 3);
        grid.add(estCust, 1, 1);
        grid.add(veggie, 1, 2);
        grid.add(dessert, 1, 3);

        return grid;
    }

    public void close(Event e) { // ohjelman sulkemisen yhteydessä varmistus
        Alert exitAlert = new Alert(AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit?");
        exitAlert.setContentText("Do you really want to exit?");
        Optional<ButtonType> result = exitAlert.showAndWait();
            if (result.get() == ButtonType.OK){
                Platform.exit();
                System.exit(0);
            } else {
                e.consume();
            }
    }
    public void checkValues(Button makeOrder, ArrayList<ChoiceBox> boxes){
        for (ChoiceBox z : boxes) {
            z.setOnAction(e -> {
                if (checkDropdowns(boxes)) {
                    makeOrder.setDisable(false);
                 }
            });
        }
    }

    public boolean checkDropdowns(ArrayList<ChoiceBox> boxes){
        for (ChoiceBox y : boxes) {
            if (y.getValue().equals(null)) {return false;}
        }
        return true;
    }
    
    public boolean checkTextFields (ArrayList <TextField> lukumaarat){
        for (TextField x : lukumaarat) {
            if (x.getText().isEmpty()) {
                
            }
        }
        return true;
    }
    
    public String calcFeedback(){
        int taso = palaute.taso(mainCourse,dessertCourse);
        double kysynta = mika.kysynta(mainCourse);
        int halukkaat = mika.halukkaat(200, kysynta);
        int ostajat = mika.ostajat(halukkaat, ruokailijat.getRuokailija());
        double tulos = mika.tulos(mainCourse, ostajat);
        int riittava = mika.riittava(halukkaat, ruokailijat.getRuokailija());
        return palaute.palaute(taso, riittava);

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
