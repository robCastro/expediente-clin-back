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

import bad.xcl.models.entity.Departamento;
import bad.xcl.models.services.IDepartamentoService;

@RestController
@RequestMapping("/departamento")
public class DepartamentoRestController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	@GetMapping("/todos")
	public List<Departamento> index(){
		return departamentoService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Departamento departamento = null;
		Map<String, Object> response = new HashMap<>();
		try {
			departamento = departamentoService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(departamento == null) {
			response.put("mensaje", "El departamento " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);	

		
	}

}
