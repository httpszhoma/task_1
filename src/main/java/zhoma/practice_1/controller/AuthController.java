package zhoma.practice_1.controller;


import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhoma.practice_1.DTO.AuthResponseDTO;
import zhoma.practice_1.DTO.LoginDto;
import zhoma.practice_1.DTO.RegisterDto;
import zhoma.practice_1.model.ClientEntity;
import zhoma.practice_1.model.UserEntity;
import zhoma.practice_1.security.JWTGenerator;
import zhoma.practice_1.service.ClientService;
import zhoma.practice_1.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    public AuthController(UserService userService, ClientService clientService, AuthenticationManager authenticationManager, JWTGenerator jwtGenerator) {
        this.userService = userService;
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername()
                        , loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok().body(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userService.existingByUsername(registerDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken");
        }

        UserEntity userEntity = userService.mapRegisterDtoToUser(registerDto);
        ClientEntity clientEntity = clientService.mapRegisterDtoToClient(registerDto);
try {
    userEntity.setClient(clientEntity);
    clientEntity.setUser(userEntity);
    clientService.registerClient(clientEntity);
    userService.addUser(userEntity);

}catch (UnsupportedOperationException e){

}


        return ResponseEntity.status(HttpStatus.CREATED).body("Client registered successfully!");
    }


}
