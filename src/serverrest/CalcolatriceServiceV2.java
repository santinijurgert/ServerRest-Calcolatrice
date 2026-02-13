/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverrest;

/**
 * Aggiunge: POTENZA, MODULO, RADICE
 * 
 */
public class CalcolatriceServiceV2 extends CalcolatriceServiceV1 {
    
    /**
     * Esegue operazioni matematiche avanzate (v2)
     * Supporta tutte le operazioni di v1 più: POTENZA, MODULO, RADICE
     * 
     * @param operando1 Il primo operando
     * @param operando2 Il secondo operando
     * @param operatore L'operatore
     * @return Il risultato dell'operazione
     * @throws IllegalArgumentException se l'operatore non è valido
     */
    public static double calcola(double operando1, double operando2, String operatore) 
            throws IllegalArgumentException {
        
        if (operatore == null || operatore.trim().isEmpty()) {
            throw new IllegalArgumentException("Operatore non può essere vuoto");
        }
        
        String op = operatore.toUpperCase().trim();
        
        return switch (op) {
            case "POTENZA", "POW", "^" -> potenza(operando1, operando2);
            case "MODULO", "MOD", "%" -> modulo(operando1, operando2);
            case "RADICE", "SQRT", "ROOT" -> radice(operando1, operando2 /*Operando2 è l'indice*/);
            default -> CalcolatriceServiceV1.calcola(operando1, operando2, operatore);
        }; // Riusa le operazioni base della classe padre (v1)
    }
    
    /**
     * Calcola la potenza
     * @param base
     * @param esponente
     * @return 
     */
    public static double potenza(double base, double esponente) {
        return Math.pow(base, esponente);
    }
    
    /**
     * Calcola il modulo (resto della divisione)
     * @param dividendo
     * @param divisore
     * @return 
     */
    public static double modulo(double dividendo, double divisore) {
        if (divisore == 0) {
            throw new IllegalArgumentException("Modulo per zero non consentito");
        }
        return dividendo % divisore;
    }
    
    /**
     * Calcola la radice n-esima
     * @param radicando
     * @param indice
     * @return 
     */
    public static double radice(double radicando, double indice) {
        if (indice == 0) {
            throw new IllegalArgumentException("Indice della radice non può essere zero");
        }
        return Math.pow(radicando, 1.0 / indice);
    }
}