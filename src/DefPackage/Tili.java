/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefPackage;

/**
 *
 * @author tiialehikoinen
 */
public class Tili {
    
    private double saldo = 0;

    public double getSaldo() {
        return saldo;

    }

    public double Otto(double maara) {

        if (maara > 0 & maara<= saldo) {
            saldo = saldo - maara;
            return maara;
            
            }
        else if (maara > saldo) {
            return 0;
        }
        else{
            return 0;
        }
    }

    public void Pano(double maara) {
        if (maara > 0) {
            saldo = saldo + maara;
        }

    }
    
}
