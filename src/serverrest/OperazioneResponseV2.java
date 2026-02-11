/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Response per API v2 con metadata aggiuntivi
 * Include: timestamp, versione_api, request_id
 * 
 * @author delfo
 */
public class OperazioneResponseV2 {
    private double operando1;
    private double operando2;
    private String operatore;
    private double risultato;
    private String operazione;
    
    // Metadata aggiuntivi per v2
    private String timestamp;
    private String versione_api;
    private String request_id;
    
    // Costruttore vuoto necessario per GSON
    public OperazioneResponseV2() {
    }
    
    // Costruttore con parametri
    public OperazioneResponseV2(double operando1, double operando2, 
                                String operatore, double risultato) {
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.operatore = operatore;
        this.risultato = risultato;
        this.operazione = String.format("%.2f %s %.2f = %.2f", 
            operando1, operatore, operando2, risultato);
        
        // Genera automaticamente i metadata
        this.timestamp = LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        );
        this.versione_api = "2.0";
        this.request_id = UUID.randomUUID().toString();
    }
    
    // Getter
    public double getOperando1() {
        return operando1;
    }
    
    public double getOperando2() {
        return operando2;
    }
    
    public String getOperatore() {
        return operatore;
    }
    
    public double getRisultato() {
        return risultato;
    }
    
    public String getOperazione() {
        return operazione;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getVersione_api() {
        return versione_api;
    }
    
    public String getRequest_id() {
        return request_id;
    }
    
    // Setter
    public void setOperando1(double operando1) {
        this.operando1 = operando1;
    }
    
    public void setOperando2(double operando2) {
        this.operando2 = operando2;
    }
    
    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }
    
    public void setRisultato(double risultato) {
        this.risultato = risultato;
    }
    
    public void setOperazione(String operazione) {
        this.operazione = operazione;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public void setVersione_api(String versione_api) {
        this.versione_api = versione_api;
    }
    
    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}