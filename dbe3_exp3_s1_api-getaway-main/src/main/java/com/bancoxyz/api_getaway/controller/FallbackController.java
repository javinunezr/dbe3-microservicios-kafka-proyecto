package com.bancoxyz.api_getaway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/transactions")
    public ResponseEntity<Map<String, Object>> transactionsFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Falla en el backend");
        response.put("mensaje", "El servicio de transacciones está caído. Inténtalo nuevamente más tarde.");
        response.put("fecha y hora", LocalDateTime.now());
        response.put("servicio", "transactions");
        response.put("fallback", true);
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/accounts")
    public ResponseEntity<Map<String, Object>> accountsFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Falla en el backend");
        response.put("mensaje", "El servicio de cuentas está caído. Inténtalo nuevamente más tarde.");
        response.put("fecha y hora", LocalDateTime.now());
        response.put("servicio", "accounts");
        response.put("fallback", true);
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }

    @GetMapping("/interests")
    public ResponseEntity<Map<String, Object>> interestsFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Falla en el backend");
        response.put("mensaje", "El servicio de intereses está caído. Inténtalo nuevamente más tarde.");
        response.put("fecha y hora", LocalDateTime.now());
        response.put("servicio", "interests");
        response.put("fallback", true);
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
