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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IPacienteDao;
import bad.xcl.models.entity.Especialidad;
import bad.xcl.models.entity.EstadoCivil;
import bad.xcl.models.entity.Hospital;
import bad.xcl.models.entity.Paciente;
import bad.xcl.models.entity.Usuario;
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
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Paciente paciente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			paciente = pacienteService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(paciente == null) {
			response.put("mensaje", "El Paciente ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
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
	
	//Actualizar Paciente
		@PutMapping("/{id}")
		public ResponseEntity<?> update(@RequestBody Paciente paciente, @PathVariable Integer id) {
			Map<String, Object> response  = new HashMap<>();
			Paciente pacienteActual = pacienteService.findById(id);
			if(pacienteActual == null) {
				response.put("mensaje","Error, no se puede editar: El Paciente con el ID:".concat(id.toString()).concat(" no existe en la base de datos"));
				return new ResponseEntity<Map>(response, HttpStatus.NOT_FOUND);
			}	
				Paciente pacienteActualizado = null;
				pacienteActual.setNombres(paciente.getNombres());
				pacienteActual.setApellidos(paciente.getApellidos());
				pacienteActual.setTelefono(paciente.getTelefono());
				pacienteActual.setUsuario(paciente.getUsuario());
				try {
					pacienteActualizado = pacienteService.save(pacienteActual);			
				}
				catch(DataAccessException e) {
					response.put("mensaje", "Error al actualizar el estado en la base de datos");
					response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				response.put("mensaje", "El usuario ha sido actualizado con exito");
				response.put("usuario", pacienteActualizado);
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
			}
			
		
	
}
