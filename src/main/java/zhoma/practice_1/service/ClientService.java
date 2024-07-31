package zhoma.practice_1.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhoma.practice_1.DTO.ClientDTO;
import zhoma.practice_1.DTO.RegisterDto;
import zhoma.practice_1.exception.ClientNotFoundException;
import zhoma.practice_1.model.ClientEntity;
import zhoma.practice_1.repository.ClientRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;

    public ClientEntity getClient(int id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));

    }
    public void updateClient(ClientDTO clientDTO, int id) {
        ClientEntity clientEntity = repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientEntity.setFIO(clientDTO.getFIO());
        clientEntity.setCar_model(clientDTO.getCar_model());
        clientEntity.setUpdate_date(clientDTO.getUpdate_date());
        clientEntity.setCar_model(clientDTO.getCar_model());
        clientEntity.setCar_model(clientDTO.getCar_model());
        clientEntity.setCar_model(clientDTO.getCar_model());

    }
    @Transactional
    public void deleteClient(int id) {
        ClientEntity clientEntity = repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientEntity.setUser(null);
        repository.delete(clientEntity);
    }
    @Transactional
    public void registerClient(ClientEntity clientEntity) {
         repository.save(clientEntity);
    }

    public ClientEntity mapRegisterDtoToClient(RegisterDto registerDto) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFIO(registerDto.getFIO());
        clientEntity.setTurbine(registerDto.getTurbine());
        clientEntity.setCar_model(registerDto.getCar_model());
        clientEntity.setPhone_number(registerDto.getPhone_number());
        clientEntity.setCreation_date(LocalDate.now());
        clientEntity.setVisit_number(0);
        return clientEntity;
    }

}
