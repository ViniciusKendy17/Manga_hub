package manga_hub.manga_hub.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import manga_hub.manga_hub.models.ProductModel;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @GetMapping("/")
    public List<ProductModel> getProducts(){
        return null;   
    }
}
