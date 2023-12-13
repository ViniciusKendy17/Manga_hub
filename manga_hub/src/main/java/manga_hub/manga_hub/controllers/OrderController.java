package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;
import manga_hub.manga_hub.Services.OrderService;
import manga_hub.manga_hub.models.OrderModel;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<OrderModel> fecharPedido(HttpServletRequest request) throws NotFoundException {
        String token = extractToken(request);

        // Fechar o pedido e obter o objeto OrderModel resultante
        OrderModel pedidoFechado = orderService.fecharPedido(token);

        // Retornar a resposta com o pedido fechado
        return ResponseEntity.ok(pedidoFechado);
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove o prefixo "Bearer " para obter apenas o token
        }
        throw new IllegalArgumentException("Token ausente ou mal formatado");
    }
}
