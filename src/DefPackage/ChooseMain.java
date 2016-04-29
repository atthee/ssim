/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;
import java.util.Scanner;

/**
 *
 * @author attelahtinen
 */
public class ChooseMain {
    public static void main(String[] args) {
        Scanner lukija = new Scanner(System.in);
        System.out.println("Graafinen vai konsoli? (1/2) ");
        int v = lukija.nextInt();
        if (v==1) {
            TestController.main(args);
        } else {
            Testiruokala.main(args);
        }
    }
}
