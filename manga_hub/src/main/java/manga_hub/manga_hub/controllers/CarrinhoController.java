package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.Services.CarrinhoService;
import manga_hub.manga_hub.models.CarrinhoModel;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class CarrinhoController {
 
    @Autowired
    private CarrinhoService pedidoService;

    @PostMapping
    public CarrinhoModel criarPedido(@RequestBody CarrinhoModel pedido) {
        return pedidoService.criarPedido(pedido);
    }

}