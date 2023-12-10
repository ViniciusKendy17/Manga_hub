package manga_hub.manga_hub.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/cart")
public class CartController {
   @Autowired
    private CartService cartService;

    @PostMapping("/")
    public ResponseEntity<CartItemModel> adicionarItemAoCarrinho(@RequestBody OrderItemsDTO itemDTO, HttpServletRequest request) throws NotFoundException{
        System.out.println("id:"+itemDTO.id());
        System.out.println("qntd:"+itemDTO.quantidade());
        String token = extractToken(request);
        System.out.println("token:"+token);

        return ResponseEntity.ok(cartService.adicionarItemAoCarrinho(itemDTO, token));
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove o prefixo "Bearer " para obter apenas o token
        }
        throw new IllegalArgumentException("Token ausente ou mal formatado");
    }
}
