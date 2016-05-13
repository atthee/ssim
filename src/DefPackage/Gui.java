/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

/**
 *
 * @author attelahtinen
 */
public class Gui extends Application {
    // Instanssimuuttujat, joista suuri osa teknisistä syistä tällä tasolla
    double paivays = 1;
    Ruokailija ruokailijat = new Ruokailija();      // Sisältää ruokailijoiden määrät
    Ruoka_annos mainCourse = new Ruoka_annos();     // Sisältää pääruuan ja lisukkeen
    Ruoka_annos veggieCourse = new Ruoka_annos();   // Sisältää kasvisruuan ja lisukkeen
    Ruoka_annos dessertCourse = new Ruoka_annos();  // Sisältää jälkiruuan
    TextArea resText = new TextArea("");            // Tulosnäkymän tyhjä tekstialue
    Tili sodexoTili = new Tili();
    DecimalFormat nf = new DecimalFormat("###0.00");
    int btnState = 0;                               // Tee tilaus -napin tilakone
    // Listat alasvetovalikoille
    ObservableList<Ruokalaji> paaruuat = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> lisukkeet = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> jalkkarit = FXCollections.observableArrayList();
    ObservableList<Ruokalaji> kasvisruuat = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // ----------- OLIOT JA MUUTTUJAT -------------------------
        Map<Double, Double> fbHistory = new HashMap(); // Palautehistoria: päivän nro ja tyytyväisyys
        fbHistory.put(0.0, 1.0);        // Nollannen päivän data, jotta ekan päivän jälkee näkyy
        Label fbToday = new Label("Tänään: #.##");              // Feedback-labelit molempiin näkymiin
        Label fbWeek = new Label("Viimeisin viikko: #.##");
        Label fbToday2 = new Label("Tänään: #.##");
        Label fbWeek2 = new Label("Viimeisin viikko: #.##");
        Tilaus tilaus = new Tilaus();
        sodexoTili.Pano(1000.0);
        Label saldoNum = new Label("" + nf.format(sodexoTili.getSaldo()));  // saldo label
        Label saldoNum2 = new Label("" + nf.format(sodexoTili.getSaldo()));
        ArrayList<Ruoka_annos> tanaan = new ArrayList();
        ArrayList<Ruoka_annos> huomenna = new ArrayList();

        TextField estCust = new TextField(); // Arvioidun normiruuan syöjien määrän laatikko
        TextField veggie = new TextField(); // Kasvisruuan syöjien määrän laatikko
        TextField dessert = new TextField(); // Jälkkärin syöjien määrän laatikko
        ArrayList<TextField> lukumaarat = new ArrayList(); // Array kenttien tarkistusta varten
        lukumaarat.add(estCust);
        lukumaarat.add(veggie);
        lukumaarat.add(dessert);

        // RUOKALAJIT
        Ruokalaji kanaKastike = new Ruokalaji("Kanakastike", 2.0, 1.5, 3.5);
        Ruokalaji riisi = new Ruokalaji("Riisi", 0.6, 0.1, 1.0);
        Ruokalaji ohukaiset = new Ruokalaji("Ohukaiset ja hillo", 0.6, 0.3, 4.2);
        Ruokalaji linssiPata = new Ruokalaji("Linssipata", 1.8, 1.1, 2.4);
        Ruokalaji peruna = new Ruokalaji("Peruna", 0.6, 0.2, 1.2);
        Ruokalaji lihaPulla = new Ruokalaji("Lihapullat", 2.0, 1.5, 3.5);
        Ruokalaji papuKastike = new Ruokalaji("Papukastike", 1.8, 1.0, 2.0);
        Ruokalaji marjaPiirakka = new Ruokalaji("Marjapiirakka", 0.6, 0.2, 3.0);
        Ruokalaji veriPalttu = new Ruokalaji("Veripalttu", 2.0, 1.7, 1.0);
        Ruokalaji tilliLiha = new Ruokalaji("Tilliliha", 2.0, 1.4, 0.6);
        Ruokalaji tacoVuoka = new Ruokalaji("Tacojauhelihavuoka", 2.0, 1.6, 3.0);
        Ruokalaji tomaattiPasta = new Ruokalaji("Tomaattikastike", 1.8, 1.2, 3.3);
        Ruokalaji pasta = new Ruokalaji("Pasta", 0.6, 0.3, 3.0);
        Ruokalaji kikHerne = new Ruokalaji("Kikhernepyörykät", 1.8, 1.2, 2.9);
        Ruokalaji mangoKiisseli = new Ruokalaji("Mangokiisseli", 0.6, 0.4, 4.5);
        Ruokalaji suklaaMousse = new Ruokalaji("Suklaamousse", 0.6, 0.3, 4.1);
        Ruokalaji kasvisPitsa = new Ruokalaji("Kasvispizza", 1.8, 1.5, 5.0);
        Ruokalaji salaatti = new Ruokalaji("Vihersalaatti", 0.6, 0.3, 3.8);
        paaruuat.addAll(kanaKastike, lihaPulla, veriPalttu, tilliLiha, tacoVuoka);
        lisukkeet.addAll(riisi, peruna, pasta, salaatti);
        jalkkarit.addAll(ohukaiset, marjaPiirakka, mangoKiisseli, suklaaMousse);
        kasvisruuat.addAll(linssiPata, papuKastike, kikHerne, tomaattiPasta, kasvisPitsa);

        //  ENSIMMÄISEN NÄKYMÄN RYHMITTYMÄ
        BorderPane borderpane = new BorderPane();
        borderpane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 70% 70%, #6EBBFF, #DEF0FF)");
        Scene orderScene = new Scene(borderpane, 700, 620);

        //  TOISEN NÄKYMÄN RYHMITTYMÄ
        BorderPane resultBorder = new BorderPane();
        resultBorder.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 70% 70%, #38A2FF, #DEF0FF)");
        Scene resultScene = new Scene(resultBorder, 700, 620);

        // OIKEAN REUNAN TEKSTIALUE
        AnchorPane textBox = new AnchorPane();
        textBox.setPadding(new Insets(10, 10, 10, 10));
        textBox.setPrefSize(300.0, 300.0);
        TextArea textA = new TextArea("Valitse päivän " + Math.round(paivays) + " ruokalista!\n");
        textBox.getChildren().add(textA);
        AnchorPane.setRightAnchor(textA, 10.0);
        AnchorPane.setTopAnchor(textA, 15.0);
        borderpane.setRight(textBox);
        textA.setPrefSize(230, 300);
        textA.setMaxSize(230, 400);

        //  ALAREUNAN LABELIT
        Label tyytyvaisyys = new Label("Tyytyväisyys");
        VBox feedbacks = new VBox();
        feedbacks.getChildren().addAll(tyytyvaisyys, fbToday, fbWeek);

        VBox saldoBox = new VBox();
        Label saldoText = new Label("Saldo:");
        saldoBox.getChildren().addAll(saldoText, saldoNum);
        saldoBox.setAlignment(Pos.CENTER_LEFT);
        
        // ALAREUNAN NAPIT JA NIIDEN EVENTHANDLERIT
        Button draftButton = modButton();   // Dropshadow-efekti toisesta metodista
        draftButton.setText("Luonnos");
        draftButton.setDisable(true);
        Button actionButton = modButton();
        actionButton.setText("Lähetä tilaus");
        actionButton.setDisable(true);

        // LUONNOS-NAPPI
        draftButton.setOnAction((ActionEvent e) -> {
            textA.clear();
            Ruoka_lista ruokalista = new Ruoka_lista(mainCourse, veggieCourse, dessertCourse);
            textA.appendText("Luonnos ruokalistasta päivälle " + Math.round(paivays) + ":\n");
            textA.appendText(ruokalista.tulostaLista());
            try {
                ruokailijat.setRuokailija(Integer.parseInt(estCust.getText()));
                ruokailijat.setKasvis(Integer.parseInt(veggie.getText()));
                ruokailijat.setJalkkari(Integer.parseInt(dessert.getText()));
                actionButton.setDisable(false);
                double hinta = tilaus.teeTilaus(ruokailijat.getRuokailija(),
                    ruokailijat.getKasvis(), ruokailijat.getJalkkari(), mainCourse, veggieCourse, dessertCourse);
                textA.appendText("Tilauksen hinta: " + nf.format(hinta) + " euroa\n\n");
            } catch (NumberFormatException nE){
               textA.appendText("\nVain numeroita kiitos!\n");
            }
        });

        // TEE TILAUS -NAPPI
        actionButton.setOnAction((ActionEvent e) -> {
            if (btnState%2==0){ // YKKÖSVAIHE
                double hinta = tilaus.teeTilaus(ruokailijat.getRuokailija(),
                        ruokailijat.getKasvis(), ruokailijat.getJalkkari(), mainCourse, veggieCourse, dessertCourse);
                double kate = sodexoTili.Otto(hinta);
                if(kate != 0){
                    textA.appendText("\nTilaus päivälle "+Math.round(paivays)+" tehty!\n"
                        + "\nAloita ruokailu painamalla nappia.");
                    saldoNum.setText("" + nf.format(sodexoTili.getSaldo()));
                    saldoNum2.setText("" + nf.format(sodexoTili.getSaldo()));
                    actionButton.setText("Aloita ruokailu");
                    draftButton.setDisable(true);
                    btnState++;
                } else {textA.appendText("\nTilauksen summa ylittää saldon!\n");}
            } else if (btnState%2==1) { // KAKKOSVAIHE
                primaryStage.setScene(resultScene);
                resText.appendText("Opiskelijoiden palaute pääruuasta: "+calcFeedback(mainCourse, 200, ruokailijat.getRuokailija()) + "\n");
                resText.appendText("Opiskelijoiden palaute kasvisruuasta: "+calcFeedback(veggieCourse, 50, ruokailijat.getKasvis()) + "\n");
                resText.appendText(calcDessert());
                saldoNum.setText("" + nf.format(sodexoTili.getSaldo()));
                saldoNum2.setText("" + nf.format(sodexoTili.getSaldo()));
                Palaute palaute = new Palaute();
                double paa = palaute.taso(mainCourse, dessertCourse);
                double vege = palaute.taso(mainCourse, dessertCourse);
                fbHistory.put(paivays, (paa * ruokailijat.getRuokailija() + vege * ruokailijat.getKasvis()) / (ruokailijat.getRuokailija() + ruokailijat.getKasvis()));
                fbToday.setText("Tänään: " + nf.format(fbHistory.get(paivays)));
                fbWeek.setText("Viikko: " + nf.format((fbHistory.get(paivays) + fbHistory.get(paivays - 1)) / 2));
                fbToday2.setText("Tänään: " + nf.format(fbHistory.get(paivays)));
                fbWeek2.setText("Viikko: " + nf.format((fbHistory.get(paivays) + fbHistory.get(paivays - 1)) / 2));
                paivays++;
                btnState++;
                actionButton.setText("Lähetä tilaus");
            }
        });
        
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setPadding(new Insets(0, 10, 0, 10));
        hbox.setStyle("-fx-background-color: #B0DAFF;");
        hbox.getChildren().addAll(draftButton, actionButton);
        hbox.setAlignment(Pos.CENTER);

        BorderPane footer = new BorderPane();
        footer.setStyle("-fx-background-color: #B0DAFF;");
        footer.setPadding(new Insets(10, 10, 10, 10));
        footer.setLeft(feedbacks);
        footer.setCenter(hbox);
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
        header.getChildren().addAll(logo, label1);
        StackPane.setAlignment(label1, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(logo, Pos.TOP_CENTER);
        borderpane.setTop(header);

        // VALINNAT
        //Choice boxes (dropdownit)
        VBox selectors = new VBox();
        ChoiceBox paaruoka = new ChoiceBox(FXCollections.observableArrayList(paaruuat));
        paaruoka.setMinWidth(150);
        paaruoka.setPrefWidth(150);
        paaruoka.getSelectionModel().selectedIndexProperty().addListener((obs, old, newSelect) -> {
            mainCourse.setPaaruoka(paaruuat.get(newSelect.intValue()));
            textA.appendText("Valinta: " + mainCourse.getPaaruoka().toString() + "\n");
        });
        ChoiceBox lisuke1 = new ChoiceBox(FXCollections.observableArrayList(lisukkeet));
        lisuke1.setMinWidth(150);
        lisuke1.getSelectionModel().selectedIndexProperty().addListener((obs, old, newSelect) -> {
            mainCourse.setLisuke(lisukkeet.get(newSelect.intValue()));
            textA.appendText("Valinta: " + mainCourse.getLisuke().toString() + "\n");
        });
        ChoiceBox kasvisruoka = new ChoiceBox(FXCollections.observableArrayList(kasvisruuat));
        kasvisruoka.setMinWidth(150);
        kasvisruoka.getSelectionModel().selectedIndexProperty().addListener((obs, old, newSelect) -> {
            veggieCourse.setPaaruoka(kasvisruuat.get(newSelect.intValue()));
            textA.appendText("Valinta: " + veggieCourse.getPaaruoka().toString() + "\n");
        });
        ChoiceBox lisuke2 = new ChoiceBox(FXCollections.observableArrayList(lisukkeet));
        lisuke2.setMinWidth(150);
        lisuke2.getSelectionModel().selectedIndexProperty().addListener((obs, old, newSelect) -> {
            veggieCourse.setLisuke(lisukkeet.get(newSelect.intValue()));
            textA.appendText("Valinta: " + veggieCourse.getLisuke().toString() + "\n");
        });
        ChoiceBox jalkkari = new ChoiceBox(FXCollections.observableArrayList(jalkkarit));
        jalkkari.setMinWidth(150);
        jalkkari.getSelectionModel().selectedIndexProperty().addListener((obs, old, newSelect) -> {
            dessertCourse.setJalkkari(jalkkarit.get(newSelect.intValue()));
            textA.appendText("Valinta: " + dessertCourse.getJalkkari().toString() + "\n");
        });

        ArrayList<ChoiceBox> boxes = new ArrayList();
        boxes.add(paaruoka);
        boxes.add(lisuke1);
        boxes.add(kasvisruoka);
        boxes.add(lisuke2);
        boxes.add(jalkkari);
        
        checkValues(draftButton,lukumaarat, boxes);
        
        Label paaLabel = new Label("Pääruoka:");
        paaLabel.setAlignment(Pos.CENTER_LEFT);
        Label vegeLabel = new Label("Kasvisruoka:");
        vegeLabel.setAlignment(Pos.CENTER_LEFT);
        Label desLabel = new Label("Jälkiruoka:");
        desLabel.setAlignment(Pos.CENTER_LEFT);
        selectors.getChildren().addAll(paaLabel,paaruoka, lisuke1,vegeLabel, kasvisruoka, lisuke2,desLabel, jalkkari);
        selectors.setPadding(new Insets(10, 20, 20, 20));
        selectors.setSpacing(5);
        selectors.setAlignment(Pos.CENTER_LEFT);

        VBox query = new VBox();
        query.setPadding(new Insets(10, 20, 10, 20));
        query.getChildren().addAll(createFields(textA, lukumaarat), selectors);
        query.setSpacing(15);
        borderpane.setLeft(query);

        // ----------- KESKIOSA -------------------------
        VBox middle = new VBox();
        Text midMessage = new Text("Tervetuloa ruokalasimulaattoriin! "
                + "Anna arviosi tänään ruokalassa syövistä opiskelijoista "
                + "sekä valitse tarjottavat ruuat valikoista. Simulaattori laskee "
                + "paljonko ravintola tuottaa.\n\n"
                + "Koulussa on n. 250 opiskelijaa ja heistä n. 20 % on kasvissyöjiä.");
        TextFlow flow = new TextFlow(midMessage);
        flow.setMinWidth(127);
        flow.setMaxWidth(300);
        midMessage.setFont(Font.font("System", FontWeight.BOLD, 12));
        midMessage.setLineSpacing(20);

        middle.setPadding(new Insets(15, 0, 15, 20));
        middle.getChildren().addAll(flow);
        middle.setAlignment(Pos.TOP_CENTER);
        borderpane.setCenter(middle);

        // ----------- AVAUSKOMENNOT -------------------------

        primaryStage.setTitle("Opiskelijaravintola");
        primaryStage.setScene(orderScene);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(620);
        primaryStage.setOnCloseRequest(e -> {
            close(e);
        });
        primaryStage.show();

        //--------------------------------------------
        //--------------------------------------------
        //------------- TOINEN NÄKYMÄ ----------------
        //--------------------------------------------
        //--------------------------------------------
        StackPane resStack = new StackPane();
        VBox resMid = new VBox();
        Button backToOrder = modButton();

        backToOrder.setText("Seuraava päivä >>");
        backToOrder.setOnAction((ActionEvent e) -> {
            textA.clear();
            textA.appendText("Valitse päivän " + Math.round(paivays) + " ruokalista!\n");
            paaruoka.getSelectionModel().clearSelection();
            lisuke1.getSelectionModel().clearSelection();
            kasvisruoka.getSelectionModel().clearSelection();
            lisuke2.getSelectionModel().clearSelection();
            jalkkari.getSelectionModel().clearSelection();
            actionButton.setDisable(true);
            draftButton.setDisable(true);
            resText.clear();
            saldoNum.setText("" + nf.format(sodexoTili.getSaldo()));
            saldoNum2.setText("" + nf.format(sodexoTili.getSaldo()));
            primaryStage.setScene(orderScene);
        });
        Rectangle rect = new Rectangle(300, 300);
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
        header2.getChildren().addAll(logo2, label2);
        StackPane.setAlignment(label2, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(logo2, Pos.TOP_CENTER);

        Label tyytyvaisyys2 = new Label("Tyytyväisyys");
        VBox feedbacks2 = new VBox();
        feedbacks2.getChildren().addAll(tyytyvaisyys2, fbToday2, fbWeek2);

        VBox saldoBox2 = new VBox();
        Label saldoText2 = new Label("Saldo:");
        saldoBox2.getChildren().addAll(saldoText2, saldoNum2);
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
    public GridPane createFields(TextArea textA, ArrayList<TextField> lukumaarat) { // luodaan tekstikentät
        GridPane grid = new GridPane();

        Label label1 = new Label("Pääruoka lkm: ");
        label1.setFont(Font.font("Cambria", 16));
        Label label2 = new Label("Kasvis lkm: ");
        label2.setFont(Font.font("Cambria", 16));
        Label label3 = new Label("Jälkiruoka lkm: ");
        label3.setFont(Font.font("Cambria", 16));

        lukumaarat.get(0).setAlignment(Pos.CENTER);
        lukumaarat.get(0).setPrefSize(50, 20);

        lukumaarat.get(1).setAlignment(Pos.CENTER);
        lukumaarat.get(1).setPrefSize(50, 20);

        lukumaarat.get(2).setAlignment(Pos.CENTER);
        lukumaarat.get(2).setPrefSize(50, 20);

        grid.setVgap(10);
        grid.setHgap(5);
        grid.add(label1, 0, 1);
        grid.add(label2, 0, 2);
        grid.add(label3, 0, 3);
        grid.add(lukumaarat.get(0), 1, 1);
        grid.add(lukumaarat.get(1), 1, 2);
        grid.add(lukumaarat.get(2), 1, 3);

        return grid;
    }

    public void close(Event e) { // ohjelman sulkemisen yhteydessä varmistus
        Alert exitAlert = new Alert(AlertType.CONFIRMATION);
        exitAlert.setHeaderText("Olet sulkemassa ohjelmaa");
        exitAlert.setTitle("Lopeta?");
        exitAlert.setContentText("Haluatko varmasti lopettaa?");
        ButtonType lopeta = new ButtonType("Lopeta");
        ButtonType peruuta = new ButtonType("Peruuta",ButtonData.CANCEL_CLOSE);
        exitAlert.getButtonTypes().setAll(lopeta,peruuta);
        Optional<ButtonType> result = exitAlert.showAndWait();
        if (result.get() == lopeta) {
            Platform.exit();
            System.exit(0);
        } else {
            e.consume();
        }
    }
    
    public Button modButton() {
        Button btn = new Button();
        DropShadow shadow = new DropShadow();
        
        btn.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            btn.setEffect(shadow);
        });
        //Removing the shadow when the mouse cursor is off
        
        btn.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            btn.setEffect(null);
        });
                        
        return btn;
    }

    public void checkValues(Button btn,ArrayList<TextField> lukumaarat,ArrayList<ChoiceBox> boxes) {
        for (ChoiceBox z : boxes) {
            z.setOnAction(e -> {
                if (checkDropdowns(boxes) && checkTextFields(lukumaarat)) {
                    btn.setDisable(false);
                    
                }
            });
        }
    }

    public boolean checkDropdowns(ArrayList<ChoiceBox> boxes) {
        for (ChoiceBox y : boxes) {
            if (y.getValue().equals(null)) {
                return false;
            }
            
        }
        return true;
    }

    public boolean checkTextFields(ArrayList<TextField> lukumaarat) {
        for (TextField x : lukumaarat) {
            if (x.getText().equals(null)) {
                return false;
            }
        }
        return true;
    }

    public String calcFeedback(Ruoka_annos course, int lkm, int annokset) {
        Kysynta mika = new Kysynta();
        Palaute palaute = new Palaute();
        double taso = palaute.taso(course, dessertCourse);
        double kysynta = mika.kysynta(course);
        int halukkaat = mika.halukkaat(lkm, kysynta);
        int ostajat = mika.ostajat(halukkaat, annokset);
        double tulos = mika.tulos(course, ostajat);
        int riittava = mika.riittava(halukkaat, annokset);
        sodexoTili.Pano(tulos);
        resText.appendText(course.toString()+" myynti oli tänään " + nf.format(tulos) + " euroa\n");
        return palaute.palaute(taso, riittava);
    }

    public String calcDessert() {
        Kysynta mika = new Kysynta();
        double kysynta = mika.jalkkariKysynta(dessertCourse);
        int halukkaat = mika.halukkaat(200, kysynta);
        int ostajat = mika.ostajat(halukkaat, ruokailijat.getJalkkari());
        double tulos = mika.jalkkariTulos(dessertCourse, ostajat);
        sodexoTili.Pano(tulos);
        return dessertCourse.getJalkkari().toString()+" myynti oli tänään " + nf.format(tulos) + " euroa\n";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
