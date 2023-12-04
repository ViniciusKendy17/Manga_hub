package manga_hub.manga_hub.Services;

import manga_hub.manga_hub.repositories.CarrinhoRepository;

public class CarrinhoService {
    private CarrinhoRepository repository;
    
    public CarrinhoService(CarrinhoRepository repository) {
        this.repository = repository;
    }

}
