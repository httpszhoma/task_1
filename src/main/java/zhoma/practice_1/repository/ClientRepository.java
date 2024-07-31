package zhoma.practice_1.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zhoma.practice_1.model.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity,Integer> {

}
