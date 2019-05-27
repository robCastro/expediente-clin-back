 package bad.xcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/estado_civil")
public class EstadoCivilRestController {

	@Autowired
	private IEstadoCivilService estadoCivilService;
	
	//Todos los Estados Civiles.
	@GetMapping("/todos")
	public List<EstadoCivil> index(){
		return estadoCivilService.listar();
	}
	
	//Estados Civiles activos.
	@GetMapping("/activos")
	public List<EstadoCivil> listarActivos(){
		return estadoCivilService.listarActivos();
	}
	
	//Buscar por ID, estado civil.
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		
		EstadoCivil estadoCivil = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			estadoCivil = estadoCivilService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje","Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(estadoCivil == null) {
			response.put("mensaje","El estado civil con el ID:".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EstadoCivil>(estadoCivil, HttpStatus.OK);
	}
	
	//Crear nuevo estado Civil.
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody EstadoCivil estadoCivil) {
		
		EstadoCivil estadoNuevo = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			estadoCivil.setId(estadoCivilService.generarId());
			estadoCivil.setActivo(true);
			estadoNuevo = estadoCivilService.save(estadoCivil);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al insertar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estado civil ha sido creado con éxito");
		response.put("estado", estadoNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Actualizar estado civil.
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody EstadoCivil estadoCivil, @PathVariable Integer id){
		EstadoCivil estadoCivilActual = estadoCivilService.findById(id);
		EstadoCivil estadoCivilUpdated = null;
		Map<String, Object> response  = new HashMap<>();
		
		if(estadoCivilActual == null) {
			response.put("mensaje","Error, no se puede editar: El estado civil con el ID:".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			estadoCivilActual.setNombre(estadoCivil.getNombre());
			estadoCivilActual.setActivo(estadoCivil.getActivo());
			estadoCivilUpdated = estadoCivilService.save(estadoCivilActual);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al actualizar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estado civil ha sido actualizado con éxito");
		response.put("estado", estadoCivilUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Eliminar fisicamente estado civil.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Map<String, Object> response  = new HashMap<>();
		try {
			estadoCivilService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al eliminar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El estado civil ha sido eliminado con éxito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	
	//Actualizar estado civil.
	@PutMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarLogicamente(@RequestBody EstadoCivil estadoCivil, @PathVariable Integer id){
		EstadoCivil estadoCivilActual = estadoCivilService.findById(id);
		EstadoCivil estadoCivilUpdated = null;
		Map<String, Object> response  = new HashMap<>();
		
		if(estadoCivilActual == null) {
			response.put("mensaje","Error, no se puede editar: El estado civil con el ID:".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			estadoCivilActual.setActivo(false);
			estadoCivilUpdated = estadoCivilService.save(estadoCivilActual);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al actualizar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El estado civil ha sido eliminado con éxito");
		response.put("estado", estadoCivilUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
