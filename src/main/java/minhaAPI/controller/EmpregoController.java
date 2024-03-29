package minhaAPI.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import minhaAPI.model.Emprego;
import minhaAPI.repository.EmpregoRepository;

@RestController
public class EmpregoController {
	
	@Autowired
    private EmpregoRepository empregoRepository;

    @RequestMapping(value = "/emprego", method = RequestMethod.GET)
    public List<Emprego> Get() {
        return empregoRepository.findAll();
    }
    
    @RequestMapping(value = "/emprego/salarioEntre{salario}/{salario2}", method = RequestMethod.GET)
	public List<Emprego> FiltrarPorSalario(@PathVariable(value = "salario") float salario, @PathVariable(value = "salario2") float salario2) {
	
		return empregoRepository.salarioEntre(salario, salario2);
	}
    
   
    
  /*  @RequestMapping(value = "/emprego/salarioEntre {salario}/{salario2}", method = RequestMethod.GET)
    public List<Emprego> FiltrarPorSalario(@PathVariable(value = "salario") float salarioMinimo, @PathVariable(value = "salario2") float salarioMaximo)
    {
    	List<Emprego> empregos = empregoRepository.findAll(); //pega todos
    	List<Emprego> filtrados = new ArrayList<Emprego>(); //recebe os filtrados
    	
    	for(int i=0; i<empregos.size(); i++) {
    		if(empregos.get(i).getSalario() >= salarioMinimo && empregos.get(i).getSalario() <= salarioMaximo) {
    			filtrados.add(empregos.get(i)); //se atende o requisito, adiciona
    		}
    		
    	}
    	
       return filtrados;
       
    } */
    
    
    
    @RequestMapping(value = "/emprego/salarioApartirDe {salario}", method = RequestMethod.GET)
    public List<Emprego> SalarioIgualMaior(@PathVariable(value = "salario") float salarioMinimo)
    {
    	List<Emprego> empregos = empregoRepository.findAll(); //pega todos
    	List<Emprego> filtrados = new ArrayList<Emprego>(); //recebe os filtrados
    	
    	for(int i=0; i<empregos.size(); i++) {
    		if(empregos.get(i).getSalario() >= salarioMinimo) {
    			filtrados.add(empregos.get(i)); //se atende o requisito, adiciona
    		}
    		
    	}
    	
       return filtrados;
       
    }
    

    @RequestMapping(value = "/emprego/{id}", method = RequestMethod.GET)
    public ResponseEntity<Emprego> GetById(@PathVariable(value = "id") Integer id)
    {
        Optional<Emprego> emprego = empregoRepository.findById(id);
        if(emprego.isPresent())
            return new ResponseEntity<Emprego>(emprego.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/emprego", method =  RequestMethod.POST)
    public Emprego Post(@Valid @RequestBody Emprego emprego)
    {
        return empregoRepository.save(emprego);
    }

    @RequestMapping(value = "/emprego/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Emprego> Put(@PathVariable(value = "id") Integer id, @Valid @RequestBody Emprego newEmprego)
    {
        Optional<Emprego> oldEmprego = empregoRepository.findById(id);
        if(oldEmprego.isPresent()){
            Emprego emprego = oldEmprego.get();
            emprego.setDescricao(newEmprego.getDescricao());
            emprego.setRequisitos(newEmprego.getRequisitos());
            emprego.setSalario(newEmprego.getSalario());
            emprego.setContato(newEmprego.getContato());
            empregoRepository.save(emprego);
            return new ResponseEntity<Emprego>(emprego, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/emprego/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id)
    {
        Optional<Emprego> emprego = empregoRepository.findById(id);
        if(emprego.isPresent()){
        	empregoRepository.delete(emprego.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
