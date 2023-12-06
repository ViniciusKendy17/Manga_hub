package manga_hub.manga_hub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class LogoutController {

    private static Set<String> tokenBlacklist = new HashSet<>();

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authorizationHeader) {

        String token = extractToken(authorizationHeader);
        tokenBlacklist.add(token);
        return ResponseEntity.ok("Logout bem-sucedido");
    }

    // Método para extrair o token do cabeçalho Authorization
    private String extractToken(String authorizationHeader) {
        // O cabeçalho Authorization geralmente tem o formato "Bearer <token>"
        return authorizationHeader.replace("Bearer ", "");
    }
}
