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

import bad.xcl.models.entity.Municipio;
import bad.xcl.models.services.IMunicipioService;

@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
@RequestMapping("/municipio")
public class MunicipioRestController {
	
	@Autowired
	private IMunicipioService municipioService;
	
	@GetMapping("/lista/{id}")
	public List<Municipio> index(@PathVariable Integer id){
		return municipioService.listar(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Municipio municipio = null;
		Map<String, Object> response = new HashMap<>();
		try {
			municipio = municipioService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(municipio == null) {
			response.put("mensaje", "El municipio " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Municipio>(municipio, HttpStatus.OK);
		
	}

}
