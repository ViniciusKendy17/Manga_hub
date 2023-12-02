package manga_hub.manga_hub.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.models.UserModel;
import manga_hub.manga_hub.repositories.UserRepository;

@RestController
@RequestMapping("/teste")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public java.util.List<UserModel> teste() {
        return userRepository.findAll();
    }
}
