package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import manga_hub.manga_hub.DTO.HomeDTO;
import manga_hub.manga_hub.DTO.ProductRegDTO;
import manga_hub.manga_hub.Services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<HomeDTO> getProductDTOById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<Page<HomeDTO>> getProductsDTOBySearch(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(productService.searchProductsPageable(name, pageable));
    }

    @PostMapping("/register")
    public ResponseEntity<String> saveProduct(@RequestBody ProductRegDTO productDTO, HttpServletRequest request) {
        String token = extractToken(request);
        productService.saveProduct(productDTO, token);
        return ResponseEntity.ok("Produto salvo com sucesso");
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Remove o prefixo "Bearer " para obter apenas o token
        }
        throw new IllegalArgumentException("Token ausente ou mal formatado");
    }
}
