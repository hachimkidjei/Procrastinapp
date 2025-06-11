package org.miage.procrastinapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔹 Gère les erreurs de type "Non trouvé"
    @ExceptionHandler({
            UtilisateurNotFoundException.class,
            PiegeNotFoundException.class,
            ConfrontationNotFoundException.class,
            DefiNotFoundException.class,
            ParticipationNotFoundException.class,
            ExcuseNotFoundException.class,
            TacheNotFoundException.class,
            RecompenseNotFoundException.class,
            RessourceIntrouvableException.class
    })
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    // 🔹 Gère les conflits (ex. : email déjà utilisé)
    @ExceptionHandler(EmailDejaUtiliseException.class)
    public ResponseEntity<Object> handleConflit(RuntimeException ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.CONFLICT, request);
    }

    // 🔹 Gère toute autre exception inattendue
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // 🔹 Méthode générique de construction de la réponse JSON
    private ResponseEntity<Object> buildResponse(Exception ex, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(body, status);
    }
}
