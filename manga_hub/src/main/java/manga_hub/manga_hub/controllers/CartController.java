package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.DTO.OrderItemsDTO;
import manga_hub.manga_hub.Services.CartService;


@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private CartService shoppingCartService;

    @PostMapping("/adicionar")
    public ResponseEntity<Void> adicionarProdutoNoCarrinho(@RequestBody OrderItemsDTO orderItemsDTO, @RequestParam Long userId) {
        shoppingCartService.adicionarProdutoNoCarrinho(orderItemsDTO, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}