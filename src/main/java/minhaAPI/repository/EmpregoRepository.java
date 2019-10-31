package minhaAPI.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import minhaAPI.model.Emprego;

@Repository
public interface EmpregoRepository extends JpaRepository<Emprego, Integer>{

}


