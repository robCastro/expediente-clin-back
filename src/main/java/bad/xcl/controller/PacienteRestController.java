package bad.xcl.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IPacienteDao;
import bad.xcl.models.entity.EstadoCivil;
import bad.xcl.models.entity.Paciente;
import bad.xcl.models.services.IPacienteService;
import bad.xcl.models.services.IUsuarioService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/paciente")
public class PacienteRestController {
	
	@Autowired
	private IPacienteService pacienteService;
	
	
	@Autowired
	private IPacienteDao pacienteDao;
	
	@GetMapping("/todos")
	public List<Paciente> index(){
		return pacienteService.findAll();
	}
	
	@GetMapping("/habilitados/{id}")	
	public List<Paciente> pacientesHabilitadosPorHospital(@PathVariable Integer id){
		List<Paciente> pacientes = new ArrayList<Paciente>();
		for (Paciente paciente: pacienteDao.listarPacientesPorHospital(id)) {
			pacientes.add(paciente);
		}
		return pacientes;
	}

	//crear nuevo paciente
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Paciente paciente)
	{
		Paciente pacienteNuevo = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			paciente.setId(pacienteService.generarId());
			pacienteNuevo = pacienteService.save(paciente);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al insertar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El paciente ha sido creado con éxito");
		response.put("estado", pacienteNuevo);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
