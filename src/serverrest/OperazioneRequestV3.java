/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

/**
 *
 * @author delfo
 */
public class OperazioneRequestV3 {
    private double valore;
    private String unita1;
    private String unita2;
    private double risultato;
    
    // Costruttore vuoto necessario per GSON
    public OperazioneRequestV3() {
    }

    public OperazioneRequestV3(double valore, String unita1, String unita2, double risultato) {
        this.valore = valore;
        this.unita1 = unita1;
        this.unita2 = unita2;
        this.risultato = risultato;
    }

    public double getValore() {
        return valore;
    }

    public void setValore(double valore) {
        this.valore = valore;
    }

    public String getUnita1() {
        return unita1;
    }

    public void setUnita1(String unita1) {
        this.unita1 = unita1;
    }

    public String getUnita2() {
        return unita2;
    }

    public void setUnita2(String unita2) {
        this.unita2 = unita2;
    }

    public double getRisultato() {
        return risultato;
    }

    public void setRisultato(double risultato) {
        this.risultato = risultato;
    }
 
    
    
}