package bad.xcl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.Consulta;
import bad.xcl.models.services.IConsultaService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/consulta")
public class ConsultaRestController {
	
	@Autowired
	private IConsultaService consultaService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Consulta consulta = null;
		Map<String, Object> response = new HashMap<>();
		try {
			consulta = consultaService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(consulta == null) {
			response.put("mensaje", "La consulta " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
	}
	
	@PutMapping("signos_vitales/{id}")
	public ResponseEntity<?> updateSignos(@Valid @RequestBody Consulta consulta, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Consulta consultaActual = consultaService.findById(consulta.getId());
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for( FieldError err: result.getFieldErrors()) {
				errors.add(err.getDefaultMessage());
			}
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if(consultaActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la consulta ".concat(consulta.getId().toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Consulta consultaActualizada = null;
		consultaActual.setTemperatura(consulta.getTemperatura());
		consultaActual.setPeso(consulta.getPeso());
		consultaActual.setEstatura(consulta.getEstatura());
		consultaActual.setPresion(consulta.getPresion());
		consultaActual.setRitmo(consulta.getRitmo());
		try {
			consultaActualizada = consultaService.save(consultaActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "La consulta ha sido actualizado con exito");
		response.put("consulta", consultaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	@PutMapping("diagnosticar/{id}")
	public ResponseEntity<?> updateDiagnostico(@RequestBody Consulta consulta) {
		Map<String, Object> response = new HashMap<>();
		Consulta consultaActual = consultaService.findById(consulta.getId());
		if(consultaActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la consulta ".concat(consulta.getId().toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		if(consulta.getEnfermedad() == null) {
			response.put("mensaje", "Error, no especificó una enfermedad para el paciente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		if(consulta.getSintoma() == null) {
			response.put("mensaje", "Error, no especificó los sintomas para el paciente");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}
		Consulta consultaActualizada = null;
		consultaActual.setSintoma(consulta.getSintoma());
		consultaActual.setEnfermedad(consulta.getEnfermedad());
		try {
			consultaActualizada = consultaService.save(consultaActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Diagnosticos y Sintomas guardados correctamente");
		response.put("consulta", consultaActualizada);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}

}
