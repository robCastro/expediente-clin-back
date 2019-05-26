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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.Hospital;
import bad.xcl.models.services.IHospitalService;

@RestController
@RequestMapping("/hospital")
public class HospitalRestController {

	@Autowired
	private IHospitalService hospitalService;
	
	//Hospitales aprobados.
	@GetMapping("/aprobados")
	public List<Hospital> aprobados(){
		return hospitalService.listarAprobados();
	}
	
	//Hospitales pendientes.
	@GetMapping("/pendientes")
	public List<Hospital> pendientes(){
		return hospitalService.listarPendientes();
	}

	//Hospitales denegados.
	@GetMapping("/denegados")
	public List<Hospital> denegados(){
		return hospitalService.listarDenegados();
	}
	
	//Todos los hospitales sin filtro.
	@GetMapping("/todos")
	public List<Hospital> index(){
		return hospitalService.findAll();
	}
	
	//Buscar por ID, hospital.
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		Hospital hospital = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			hospital = hospitalService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje","Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(hospital == null) {
			response.put("mensaje","El hospital con el ID:".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Hospital>(hospital, HttpStatus.OK);
	}
	
	//Crear nuevo hospital.
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Hospital hospital) {
		
		Hospital hospitalNuevo = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			hospital.setId(hospitalService.generarId());
			hospital.setActivo(true);
			hospitalNuevo = hospitalService.save(hospital);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al insertar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El hospital ha sido creado con éxito");
		response.put("estado", hospitalNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Actualizar Hospital.
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Hospital hospital, @PathVariable Integer id) {
		Hospital hospitalActual = hospitalService.findById(id);
		Hospital hospitalUpdated = null;
		Map<String, Object> response  = new HashMap<>();
		
		if(hospitalActual == null) {
			response.put("mensaje","Error, no se puede editar: El hospital con el ID:".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
		}
		try {	
			hospitalActual.setNombre(hospital.getNombre());
			hospitalActual.setFecha(hospital.getFecha());
			hospitalActual.setTelefono(hospital.getTelefono());
			hospitalActual.setDetalle(hospital.getDetalle());
			hospitalActual.setAprobado(hospital.getAprobado());
			hospitalActual.setActivo(hospital.getActivo());
			hospitalActual.setPais(hospital.getPais());
			hospitalActual.setMunicipio(hospital.getMunicipio());
			
			hospitalUpdated = hospitalService.save(hospitalActual);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al actualizar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
		response.put("mensaje", "El hospital ha sido actualizado con éxito");
		response.put("estado", hospitalUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Eliminar fisicamente hospital de la base de datos.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response  = new HashMap<>();
		try {
			hospitalService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al eliminar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El hospital ha sido eliminado con éxito");
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		
	}
	
}
