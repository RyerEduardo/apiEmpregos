package minhaAPI.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import minhaAPI.model.Emprego;

@Repository
public interface EmpregoRepository extends JpaRepository<Emprego, Integer>{
	@Query(value = "SELECT * FROM emprego WHERE emprego.salario >= :salario and emprego.salario <= :salario2;", nativeQuery = true)
	List <Emprego> salarioEntre(float salario, float salario2);

}


