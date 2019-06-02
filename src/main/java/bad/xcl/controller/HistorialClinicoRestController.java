package bad.xcl.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.HistorialClinico;
import bad.xcl.models.services.IHistorialClinicoService;


@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/historial")
public class HistorialClinicoRestController {
	@Autowired
	private IHistorialClinicoService historialService;
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody HistorialClinico historial, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		HistorialClinico historialActual = historialService.findById(id);
		if(historialActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el historial clínico ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		HistorialClinico historialActualizado = null;
		historialActual.setFecha(historial.getFecha());
		historialActual.setEnfermedad(historial.getEnfermedad());
		try {
			historialActualizado = historialService.save(historialActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Historial Clinico ha sido actualizado con exito");
		response.put("historial", historialActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}	
	@GetMapping("/{id}")
	public List<HistorialClinico> listarPorPaciente(@PathVariable Integer id){
		return historialService.listarPorPaciente(id);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody HistorialClinico historial) {
		HistorialClinico historialNuevo = null;
		historial.setActivo(true);
		Map<String, Object> response = new HashMap<>();
		try {
			historialNuevo = historialService.guardar(historial);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El historial clinico ha sido creado con exito");
		response.put("historial", historialNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			historialService.eliminar(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el historial clinico de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El historial clinico ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
