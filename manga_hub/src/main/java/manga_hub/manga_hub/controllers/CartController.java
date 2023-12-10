package manga_hub.manga_hub.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;
import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.DTO.ProductRegDTO;
import manga_hub.manga_hub.Services.CartService;
import manga_hub.manga_hub.models.CartItemModel;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/")
    public void adicionarItemAoCarrinho(@RequestBody OrderItemsDTO itemDTO, HttpServletRequest request) throws NotFoundException{
        String token = extractToken(request);
        cartService.adicionarItemAoCarrinho(itemDTO, token);
        //return ResponseEntity.ok();
    }

    @GetMapping("/")
    public ResponseEntity<List<CartItemModel>> getItens(HttpServletRequest request) throws NotFoundException {
        String token = extractToken(request);
        return ResponseEntity.ok(cartService.listItens(token));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerItemDoCarrinho(@PathVariable Long id, HttpServletRequest request) throws NotFoundException {
        String token = extractToken(request);
        cartService.removerItemDoCarrinho(id, token);
        return ResponseEntity.ok().build();
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove o prefixo "Bearer " para obter apenas o token
        }
        throw new IllegalArgumentException("Token ausente ou mal formatado");
    }
}
