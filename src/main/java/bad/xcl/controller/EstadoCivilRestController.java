package bad.xcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.EstadoCivil;
import bad.xcl.models.services.IEstadoCivilService;

@RestController
@RequestMapping("/estado_civil")
public class EstadoCivilRestController {

	@Autowired
	private IEstadoCivilService estadoCivilService;
	
	@GetMapping("/todos")
	public List<EstadoCivil> index(){
		return estadoCivilService.listar();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EstadoCivil show(@PathVariable Integer id){
		return estadoCivilService.findById(id);
	}
	
	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoCivil create(@RequestBody EstadoCivil estadoCivil) {
		estadoCivil.setId(estadoCivilService.generarId());
		return estadoCivilService.save(estadoCivil);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoCivil update(@RequestBody EstadoCivil estadoCivil, @PathVariable Integer id){
		EstadoCivil estadoCivilActual = estadoCivilService.findById(id);
		estadoCivilActual.setNombre(estadoCivil.getNombre());
		estadoCivilActual.setActivo(estadoCivil.getActivo());
		
		return estadoCivilService.save(estadoCivilActual);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		estadoCivilService.delete(id);
	}
	
	
}
