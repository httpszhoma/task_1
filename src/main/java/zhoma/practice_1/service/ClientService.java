package zhoma.practice_1.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zhoma.practice_1.DTO.ClientDTO;
import zhoma.practice_1.DTO.RegisterDto;
import zhoma.practice_1.DTO.UpdateDTO;
import zhoma.practice_1.exception.ClientNotFoundException;
import zhoma.practice_1.model.ClientEntity;
import zhoma.practice_1.model.UserEntity;
import zhoma.practice_1.repository.ClientRepository;
import zhoma.practice_1.repository.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final UserRepository userRepository;
    public ClientEntity getClient(int id) {
        return repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));

    }
    public void updateClient(UpdateDTO updateDTO, int id) {
        ClientEntity clientEntity = repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientEntity.setFIO(updateDTO.getFIO());
        clientEntity.setCar_model(updateDTO.getCar_model());
        clientEntity.setTurbine(updateDTO.getTurbine());
        clientEntity.setPhone_number(updateDTO.getPhone_number());
        clientEntity.setUpdate_date(LocalDate.now());
repository.save(clientEntity);

    }
    @Transactional
    public void deleteClient(int id) {
        ClientEntity clientEntity = repository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        clientEntity.setUser(null);
        repository.delete(clientEntity);
       userRepository.deleteById(id);

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
