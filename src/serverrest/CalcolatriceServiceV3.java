/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;



public class CalcolatriceServiceV3{
    
    //Previsto da metri a iarde
    public static double calcola(double valore, String unita1, String unita2) {
         
        if (unita1 == "mt" && unita2 == "yd")
        {
            valore = valore * 0.944;
            return valore;
        }
        else
        {
            valore = valore / 0.944;
            return valore;
        }
    }


}
    

   
