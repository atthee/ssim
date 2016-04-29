/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

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

/**
 *
 * @author attelahtinen
 */
public class Guitesti3 {
    double paivays = 1;
    boolean erMesVis = false;
    TextArea textA = new TextArea("Welcome!\n");
    int ruokailijat;
    int kasvis;
    int jalkiruoka;
    Palaute palaute = new Palaute();
    Map<Double,Double> fbHistory = new HashMap();
    double rahaMeno;
    Tili sodexoTili = new Tili();
    VBox feedbacks = new VBox(); // tyytyväisyyksien wräpperi
    VBox saldoBox = new VBox(); // saldon wräpperi
    Buttons buttons = new Buttons(); // alareunan painikkeet
    StackPane top = new StackPane(); // yläreunan wräpperi
    BorderPane bottom = new BorderPane(); // alareunan wräpperi
    VBox middle = new VBox(); // keskialueen wräpperi
    VBox left = new VBox(); //vasemman laidan wräpperi
    AnchorPane right = new AnchorPane(); // oikean laidan wräpperi
    Label errorMes = new Label("This is text"); // punainen virheteksti
    VBox selectors = new VBox(); // dropdownit
    Button submitOrder = new Button(); // tee tilaus -painike
    ChoiceBox paaruoka = new ChoiceBox();
    ChoiceBox lisuke1 = new ChoiceBox();
    ChoiceBox kasvisruoka = new ChoiceBox();
    ChoiceBox lisuke2 = new ChoiceBox();
    ChoiceBox jalkkari = new ChoiceBox();
    
    
    // ---------------- OIKEA REUNA -------------------
    public AnchorPane oikeareuna(){
        // Tekstialue
        textA.setPrefSize(230, 300);
        textA.setMaxSize(230, 400);
        right.setPadding(new Insets(15, 15, 15, 15));
        right.setPrefSize(300.0, 300.0);
        right.getChildren().add(textA);
        return right;
    }
    
    // ---------------- ALAREUNA -------------------
    public BorderPane alareuna(double saldo, double today, double week){
        tyytyTekstit(today, week);
        showSaldo(saldo);
        bottom.setStyle("-fx-background-color: #B0DAFF;");
        bottom.setPadding(new Insets(10, 10, 10, 10));
        bottom.setLeft(feedbacks);
        bottom.setCenter(buttons.modButtons(textA));
        bottom.setRight(saldoBox);
        return bottom;
    }
    public void tyytyTekstit (double today, double week){
        Label tyytyvaisyys = new Label ("Tyytyväisyys");
        Label fbToday = new Label("Tänään: "+today);
        Label fbWeek = new Label ("Viimeisin viikko: #muokkaa"+week);
        feedbacks.getChildren().addAll(tyytyvaisyys,fbToday,fbWeek);
    }
    public void showSaldo(double saldo){
        Label saldoText = new Label ("Saldo:");
        Label saldoNum = new Label (""+saldo); //vaihda saldo
        saldoBox.getChildren().addAll(saldoText,saldoNum);
        saldoBox.setAlignment(Pos.CENTER_LEFT);
    }

    // ---------------- YLÄREUNA -------------------
    public StackPane ylareuna(){   
        top.setPadding(new Insets(15, 15, 15, 15));
        ImageView logo = new ImageView(new Image("file:sorekso.png"));
        logo.setPreserveRatio(true);
        logo.setFitWidth(350);
        Label label1 = new Label("Simulator 1.0");
        label1.setTextFill(Color.web("#2A3C93"));
        label1.setFont(Font.font("Cambria", FontWeight.SEMI_BOLD, 18));
        top.getChildren().addAll(logo,label1);
        StackPane.setAlignment(label1, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(logo, Pos.TOP_CENTER);
        return top;
    }
    
    // ---------------- VASEN REUNA -------------------
    public VBox vasenreuna(ObservableList paaruuat, ObservableList lisukkeet, ObservableList kasvisruuat, ObservableList jalkkarit){
        taytaValikko(paaruoka, paaruuat);
        taytaValikko(lisuke1, lisukkeet);
        taytaValikko(kasvisruoka, kasvisruuat);
        taytaValikko(lisuke2, lisukkeet);
        taytaValikko(jalkkari, jalkkarit);
        
        selectors.getChildren().addAll(paaruoka,lisuke1,kasvisruoka,lisuke2,jalkkari);
        selectors.setPadding(new Insets(20,20,20,20));
        selectors.setSpacing(10);
        selectors.setAlignment(Pos.CENTER_RIGHT);
        
        left.setPadding(new Insets(20,20,20,20));
        left.getChildren().addAll(createFields(),selectors);
        left.setSpacing(20);
        return left;
    }
    public void taytaValikko(ChoiceBox box, ObservableList list){
        box.setItems(FXCollections.observableArrayList (list));
        box.setPrefWidth(100);
        box.getSelectionModel().selectedIndexProperty().addListener((obs, oldSelect, newSelect) ->{
            textA.appendText("Valinta: "+ list.get(newSelect.intValue()).toString()+"\n");
            });
    }
    public GridPane createFields() { //käytetään left.addissa
        GridPane grid = new GridPane();
        
        Label label1 = new Label("Ruokalijat: ");
        label1.setFont(Font.font("Cambria", 16));
        Label label2 = new Label("Kasvis-%: ");
        label2.setFont(Font.font("Cambria", 16));
        Label label3 = new Label("Jälkiruoka: ");
        label3.setFont(Font.font("Cambria", 16));
        TextField estCust = fields();
        TextField veggie = fields();
        TextField dessert = fields();
        
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
    public TextField fields(){
        TextField field = new TextField();
        field.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                textA.appendText(field.getText()+"\n");
            }
        });
        field.setAlignment(Pos.CENTER);
        field.setPrefSize(60, 20);
        return field;
    }
    
    // ---------------- KESKUSTA -------------------
    public VBox keskusta(){
        // Tee tilaus -painike
        errorMes.setTextFill(Color.RED);
        errorMes.setVisible(erMesVis);
        submitOrder.setText("Tee tilaus");
        submitOrder.setOnAction((ActionEvent e) -> {
            textA.appendText("Tilaus päivälle "+Math.round(paivays)+" tehty!\n");
            paivays++;
            erMesVis = !erMesVis;
            errorMes.setVisible(erMesVis);
        });
        middle.getChildren().addAll(errorMes,submitOrder);
        middle.setSpacing(50);
        middle.setAlignment(Pos.CENTER);
        return middle;
    }
    
            
    public void close(Event e) {
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

}
