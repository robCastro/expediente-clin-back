package bad.xcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.Enfermedad;
import bad.xcl.models.services.IEnfermedadService;

@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
@RequestMapping("/enfermedad")
public class EnfermedadRestController {
	
	@Autowired
	private IEnfermedadService enfermedadService;
	
	@GetMapping("/lista/{id}")
	public List<Enfermedad> index(@PathVariable Integer id){
		return enfermedadService.listar(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Enfermedad enfermedad = null;
		Map<String, Object> response = new HashMap<>();
		try {
			enfermedad = enfermedadService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(enfermedad == null) {
			response.put("mensaje", "La Enfermedad " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Enfermedad>(enfermedad, HttpStatus.OK);
		
	}

}
