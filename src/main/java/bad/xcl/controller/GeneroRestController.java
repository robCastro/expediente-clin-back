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

import bad.xcl.models.entity.Genero;
import bad.xcl.models.services.IGeneroService;

@RestController
@RequestMapping("/genero/")
public class GeneroRestController {
	
	@Autowired
	private IGeneroService generoService;
	
	//Buscar Todos
	@GetMapping("/generos")
	public List<Genero> index(){
		return generoService.findAll();
	}
	
	@GetMapping("genero/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Genero genero = null;
		Map<String, Object> response = new HashMap<>();
		try {
			genero = generoService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(genero == null) {
			response.put("mensaje", "El Genero ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Genero>(genero, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Genero genero) {
		Genero generoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			
			generoNew = generoService.guardarGenero(genero);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Genero ha sido creado con exito");
		response.put("genero", generoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/generos/{id}")
	public ResponseEntity<?> update(@RequestBody Genero genero, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Genero generoActual = generoService.findById(id);
		if(generoActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el genero ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Genero generoActualizado = null;
		generoActual.setNombre(genero.getNombre());
		generoActual.setActivo(genero.getActivo());
		try {
			generoActualizado = generoService.save(generoActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El genero ha sido actualizado con exito");
		response.put("genero", generoActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/generos/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {

		Map<String, Object> response = new HashMap<>();
		try {
			Genero generoActual = generoService.findById(id);
			generoActual.setActivo(false);
			generoService.save(generoActual);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el genero de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El genero ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}

