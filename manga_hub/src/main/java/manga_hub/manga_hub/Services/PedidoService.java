package manga_hub.manga_hub.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import manga_hub.manga_hub.models.PedidoModel;
import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.PedidoRepository;
import manga_hub.manga_hub.repositories.UserRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository PedidoRepository;
    
    public PedidoModel criarPedido(PedidoModel pedido) {
        /* 
        UserModel cliente = UserRepository.findById(cliente.getId()).orElseThrow();
        */

        
        
        return PedidoRepository.save(pedido);
    }

}
