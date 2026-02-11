/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package serverrest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Handler per richieste GET API v2
 * Supporta operazioni avanzate: POTENZA, MODULO, RADICE
 * 
 * @author delfo
 */
public class GetHandlerV2 implements HttpHandler {
    
    // Istanza Gson configurata per pretty printing
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        
        // Verifica che sia una richiesta GET
        if (!exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            inviaErrore(exchange, 405, "Metodo non consentito. Usa GET");
            return;
        }
        
        try {
            // Estrae i parametri dalla query string
            Map<String, String> parametri = estraiParametri(exchange.getRequestURI().getQuery());
            
            // Validazione parametri
            if (!parametri.containsKey("operando1") || 
                !parametri.containsKey("operando2") || 
                !parametri.containsKey("operatore")) {
                inviaErrore(exchange, 400, 
                    "Parametri mancanti. Necessari: operando1, operando2, operatore");
                return;
            }
            
            // Parsing dei valori
            double operando1 = Double.parseDouble(parametri.get("operando1"));
            double operando2 = Double.parseDouble(parametri.get("operando2"));
            String operatore = parametri.get("operatore");
            
            // Esegue il calcolo con il service v2 (supporta operazioni avanzate)
            double risultato = CalcolatriceServiceV2.calcola(operando1, operando2, operatore);
            
            // Crea l'oggetto risposta v2 (con metadata automatici)
            OperazioneResponseV2 response = new OperazioneResponseV2(
                operando1,
                operando2,
                operatore,
                risultato
            );
            
            // GSON converte automaticamente l'oggetto Java in JSON
            String jsonRisposta = gson.toJson(response);
            
            inviaRisposta(exchange, 200, jsonRisposta, response.getRequest_id());
            
        } catch (NumberFormatException e) {
            inviaErrore(exchange, 400, "Operandi non validi. Devono essere numeri");
        } catch (IllegalArgumentException e) {
            inviaErrore(exchange, 400, e.getMessage());
        } catch (IOException e) {
            inviaErrore(exchange, 500, "Errore interno del server: " + e.getMessage());
        }
    }
    
    /**
     * Estrae i parametri dalla query string
     */
    private Map<String, String> estraiParametri(String query) {
        Map<String, String> parametri = new HashMap<>();
        
        if (query == null || query.isEmpty()) {
            return parametri;
        }
        
        String[] coppie = query.split("&");
        for (String coppia : coppie) {
            String[] keyValue = coppia.split("=");
            if (keyValue.length == 2) {
                try {
                    String chiave = URLDecoder.decode(keyValue[0], "UTF-8");
                    String valore = URLDecoder.decode(keyValue[1], "UTF-8");
                    parametri.put(chiave, valore);
                } catch (UnsupportedEncodingException e) {
                    // Ignora parametri malformati
                }
            }
        }
        
        return parametri;
    }
    
    /**
     * Invia una risposta di successo con header v2
     */
    private void inviaRisposta(HttpExchange exchange, int codice, String jsonRisposta, String requestId) 
            throws IOException {
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("API-Version", "2.0");
        exchange.getResponseHeaders().set("X-Request-ID", requestId);
        
        byte[] bytes = jsonRisposta.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(codice, bytes.length);
        
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
    
    /**
     * Invia una risposta di errore
     */
    private void inviaErrore(HttpExchange exchange, int codice, String messaggio) 
            throws IOException {
        
        Map<String, Object> errore = new HashMap<>();
        errore.put("errore", messaggio);
        errore.put("codice", codice);
        errore.put("versione_api", "2.0");
        
        String jsonErrore = gson.toJson(errore);
        
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("API-Version", "2.0");
        
        byte[] bytes = jsonErrore.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(codice, bytes.length);
        
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}