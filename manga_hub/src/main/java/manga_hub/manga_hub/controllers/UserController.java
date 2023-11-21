package manga_hub.manga_hub.controllers;

import java.util.List;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repository.UserRespository;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRespository userRepository;

    @GetMapping("/check-database")
    public void checkDatabaseConnection() {
        System.out.println(userRepository.findAll());

    }
}
