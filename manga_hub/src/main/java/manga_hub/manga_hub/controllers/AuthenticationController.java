package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.DTO.LoginTokenDTO;
import manga_hub.manga_hub.DTO.UserLoginDTO;
import manga_hub.manga_hub.DTO.UserRegDTO;
import manga_hub.manga_hub.Security.TokenService;
//import manga_hub.manga_hub.Security.TokenService;
import manga_hub.manga_hub.Services.AuthenticationService;
import manga_hub.manga_hub.models.UserModel;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService service;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    // localhost:8080/auth/login
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return (ResponseEntity) ResponseEntity.ok(new LoginTokenDTO(token));
    }

    // localhost:8080/auth/register
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRegDTO userDTOreg) {
        if (this.service.loadUserByUsername(userDTOreg.login()) != null)
            return ResponseEntity.badRequest().build();

        String encriptedPassowrd = new BCryptPasswordEncoder().encode(userDTOreg.senha());
        UserRegDTO userDto = new UserRegDTO(userDTOreg.nome(), userDTOreg.login(), encriptedPassowrd, userDTOreg.cep(),userDTOreg.telefone());
        service.save(userDto);
        return ResponseEntity.ok().build();
    }
}
