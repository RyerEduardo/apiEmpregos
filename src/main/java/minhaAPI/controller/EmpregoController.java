package minhaAPI.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
