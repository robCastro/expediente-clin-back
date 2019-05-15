package bad.xcl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.Rol;
import bad.xcl.models.services.IRolService;

@RestController
@RequestMapping("/rol/")
public class RolRestController {
	
	@Autowired
	private IRolService rolService;
	
	@GetMapping("/todos")
	public List<Rol> index(){
		return rolService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id){
		Rol rol = null;
		Map<String, Object> response = new HashMap<>();
		try {
			rol = rolService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(rol == null) {
			response.put("mensaje", "El rol " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}
}
