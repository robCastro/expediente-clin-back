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
	
	@GetMapping("/todos")
	public List<Especialidad> index(){
		return especialidadService.listar();
	}
	
	@GetMapping("/activos")
	public List<Especialidad> activos(){
		return especialidadService.listarActivos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		Especialidad especialidad = null;
		Map<String, Object> response = new HashMap<>();
		try {
			especialidad = especialidadService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(especialidad == null) {
			response.put("mensaje", "La especialidad " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Especialidad especialidad) {
		Especialidad especialidadNuevo = null;
		especialidad.setActivo(true);
		Map<String, Object> response = new HashMap<>();
		try {
			especialidadNuevo = especialidadService.guardar(especialidad);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La especialidad ha sido creado con exito");
		response.put("especialidad", especialidadNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Especialidad especialidad, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Especialidad especialidadActual = especialidadService.findById(id);
		if(especialidadActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la especialidad ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Especialidad especialidadActualizado = null;
		especialidadActual.setNombre(especialidad.getNombre());
		especialidadActual.setActivo(especialidad.getActivo());
		try {
			especialidadActualizado = especialidadService.save(especialidadActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la especialidad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La especialidad ha sido actualizada con exito");
		response.put("especialidad", especialidadActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			especialidadService.eliminar(id);
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
