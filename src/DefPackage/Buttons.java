/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author attelahtinen
 */
public class Buttons {
    Button btn1 = new Button();
    Button btn2 = new Button();

    public HBox modButtons(TextArea textA) {

        btn1.setText("Liian vähän ruokaa");
        btn1.setPrefSize(145, 20);
        btn2.setText("Liian pahaa ruokaa");
        btn2.setPrefSize(145, 20);

        btn1.setOnAction((ActionEvent event) -> {
            textA.appendText("Liian vähän ruokaa\n");
        });

        btn2.setOnAction((ActionEvent event) -> {
            textA.appendText("Liian pahaa ruokaa\n");
        });
        
        DropShadow shadow = new DropShadow();
        
        btn1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            btn1.setEffect(shadow);
        });
        //Removing the shadow when the mouse cursor is off
        
        btn1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            btn1.setEffect(null);
        });
        
        btn2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
            btn2.setEffect(shadow);
        });
        //Removing the shadow when the mouse cursor is off
        
        btn2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
            btn2.setEffect(null);
        });
        
        HBox hbox = new HBox();
        hbox.setSpacing(20);
        hbox.setPadding(new Insets(0,10,0,10));
        hbox.setStyle("-fx-background-color: #B0DAFF;");
        hbox.getChildren().addAll(btn1, btn2);
        hbox.setAlignment(Pos.CENTER_LEFT);
        
        return hbox;
    }
}
