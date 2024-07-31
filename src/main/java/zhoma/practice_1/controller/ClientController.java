package zhoma.practice_1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhoma.practice_1.DTO.ClientDTO;
import zhoma.practice_1.model.ClientEntity;
import zhoma.practice_1.service.ClientService;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable(name = "id")int id){
        ClientEntity client = service.getClient(id);
        return ResponseEntity.ok().body(client);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateClient(@PathVariable(name = "id")int id,@RequestBody ClientDTO clientDTO){
            service.updateClient(clientDTO,id);
            return ResponseEntity.ok().body("Client updated successfully !!!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(name = "id")int id){
        service.deleteClient(id);
        return ResponseEntity.ok().body("Client deleted successfully !!!");
    }







}
