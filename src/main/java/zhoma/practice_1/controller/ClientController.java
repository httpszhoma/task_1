package zhoma.practice_1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhoma.practice_1.DTO.ClientDTO;
import zhoma.practice_1.DTO.UpdateDTO;
import zhoma.practice_1.model.ClientEntity;
import zhoma.practice_1.service.ClientService;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Auth")
@Tag(name = "Client", description = "Operations related to clients")
public class ClientController {

    private final ClientService service;

    @Operation(summary = "Get client by ID", description = "Retrieve a client using their unique ID.")
    @GetMapping("/get/{id}")
    public ResponseEntity<ClientEntity> getClient(@PathVariable(name = "id") int id) {
        ClientEntity client = service.getClient(id);
        return ResponseEntity.ok().body(client);
    }

    @Operation(summary = "Update client details", description = "Update the details of a client using their unique ID.")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateClient(@PathVariable(name = "id") int id, @RequestBody UpdateDTO updateDTO) {
        service.updateClient(updateDTO, id);
        return ResponseEntity.ok().body("Client updated successfully !!!");
    }

    @Operation(summary = "Delete client by ID", description = "Delete a client using their unique ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable(name = "id") int id) {
        service.deleteClient(id);
        return ResponseEntity.ok().body("Client deleted successfully !!!");
    }
}
