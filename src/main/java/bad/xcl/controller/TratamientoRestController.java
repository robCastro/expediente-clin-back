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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.Tratamiento;
import bad.xcl.models.services.ITratamientoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/tratamiento")
public class TratamientoRestController {
	@Autowired
	private ITratamientoService tratamientoService;
	
	@GetMapping("lista/{id}")
	public List<Tratamiento> listarPorConsulta(@PathVariable Integer id){
		return tratamientoService.listarPorConsulta(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Tratamiento tratamiento) {
		Tratamiento tratamientoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		try {
			tratamientoNuevo = tratamientoService.guardar(tratamiento);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tratamiento clinico ha sido creado con exito");
		response.put("tratamiento", tratamientoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			tratamientoService.delete(id);;
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el tratamiento de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El tratamiento ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
