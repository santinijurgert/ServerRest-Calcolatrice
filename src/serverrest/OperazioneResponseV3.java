/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Response per API v3 specializzata in conversioni
 * 
 * @author jurgert
 */
public class OperazioneResponseV3 {
    
    //Valori di conversione
    private double valore;
    private String unita1;
    private String unita2;
    private double risultato;
    private String conversione;
    
    // Metadata aggiuntivi
    private String timestamp;
    private String versione_api;
    private String request_id;
    
    // Costruttore vuoto necessario per GSON
    public OperazioneResponseV3() {
    }

    public OperazioneResponseV3(double valore, String unita1, String unita2, double risultato) {
        this.valore = valore;
        this.unita1 = unita1;
        this.unita2 = unita2;
        this.risultato = risultato;
        this.conversione = String.format("%.2f %s = %.2f %.2s", 
            valore, unita1, risultato, unita2);
        
        // Genera automaticamente i metadata
        this.timestamp = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        this.versione_api = "3.0";
        this.request_id = UUID.randomUUID().toString();
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

    public String getConversione() {
        return conversione;
    }

    public void setConversione(String conversione) {
        this.conversione = conversione;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVersione_api() {
        return versione_api;
    }

    public void setVersione_api(String versione_api) {
        this.versione_api = versione_api;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
    
    
}
    