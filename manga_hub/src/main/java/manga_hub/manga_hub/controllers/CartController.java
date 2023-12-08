package manga_hub.manga_hub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.Services.CartService;
import manga_hub.manga_hub.models.OrderItemsModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/shoppingCart")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private CartService shoppingCartService;

    @GetMapping("/list")
    public ResponseEntity<List<OrderItemsModel>> listProductsInCart( @RequestParam HttpServletRequest request) {
        String token = extractToken(request);
        return ResponseEntity.status(200).body(shoppingCartService.listProductsInCart(token));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody OrderItemsDTO orderItemsDTO, @RequestParam HttpServletRequest request) {
        String token = extractToken(request);
        shoppingCartService.addProduct(orderItemsDTO, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId, @RequestParam HttpServletRequest request) {
        String token = extractToken(request);
        shoppingCartService.deleteProduct(productId, token);
        return ResponseEntity.status(204).build();
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove o prefixo "Bearer " para obter apenas o token
        }
        throw new IllegalArgumentException("Token ausente ou mal formatado");
    }

 

  


}