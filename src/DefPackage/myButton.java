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
public class myButton {
    Button btn = new Button();
    
    public Button modButton() {

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
    
}
