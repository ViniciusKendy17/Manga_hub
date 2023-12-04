package manga_hub.manga_hub.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manga_hub.manga_hub.models.CarrinhoModel;
import manga_hub.manga_hub.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository CarrinhoRepository;
    
    public CarrinhoModel criarPedido(CarrinhoModel pedido) {
        return CarrinhoRepository.save(pedido);
    }

}
