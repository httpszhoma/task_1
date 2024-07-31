package zhoma.practice_1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zhoma.practice_1.model.ClientEntity;
import zhoma.practice_1.service.ClientService;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final ClientService service;
    @PostMapping("accepting/{id}")
    public ResponseEntity<String> accept(@PathVariable(name = "id")int id){
        ClientEntity clientEntity = service.getClient(id);
        clientEntity.setAcception_date(LocalDate.now());
        service.registerClient(clientEntity);
        return ResponseEntity.ok().body("Client accepted by admin");
    }


}
