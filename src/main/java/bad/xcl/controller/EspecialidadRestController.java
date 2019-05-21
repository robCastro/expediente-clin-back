package bad.xcl.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.Especialidad;
import bad.xcl.models.services.IEspecialidadService;

@RestController
@RequestMapping("/especialidad/")
public class EspecialidadRestController {
	
	@Autowired
	private IEspecialidadService especialidadService;
	
	//Buscar Todos
	@GetMapping("/especialidades")
	public List<Especialidad> index(){
		return especialidadService.findAll();
	}
	
	@GetMapping("especialidad/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Especialidad especialidad = null;
		Map<String, Object> response = new HashMap<>();
		try {
			especialidad = especialidadService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(especialidad == null) {
			response.put("mensaje", "La Especialidad ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Especialidad especialidad) {
		Especialidad especialidadNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			especialidadNew = especialidadService.guardarEspecialidad(especialidad);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La especialidad ha sido creada con exito");
		response.put("especialidad", especialidadNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/especialidades/{id}")
	public ResponseEntity<?> update(@RequestBody Especialidad especialidad, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Especialidad especialidadActual = especialidadService.findById(id);
		if(especialidadActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la especialidad ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Especialidad especialidadActualizada = null;
		especialidadActual.setNombre(especialidad.getNombre());
		especialidadActual.setActivo(especialidad.getActivo());
		try {
			especialidadActualizada = especialidadService.save(especialidadActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La especialidad ha sido actualizado con exito");
		response.put("especialidad", especialidadActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/especialidad/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		Map<String, Object> response = new HashMap<>();
		try {
			Especialidad especialidadActual = especialidadService.findById(id);
			especialidadActual.setActivo(false);
			especialidadService.save(especialidadActual);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la especialidad de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La especialidad ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

