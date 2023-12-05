package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.Services.PedidoService;
import manga_hub.manga_hub.models.PedidoModel;

@RestController
@RequestMapping("/carrinho")
@CrossOrigin("*")
public class PedidoController {
 
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public PedidoModel criarPedido(@RequestBody PedidoModel pedido) {
        return pedidoService.criarPedido(pedido);
    }

}