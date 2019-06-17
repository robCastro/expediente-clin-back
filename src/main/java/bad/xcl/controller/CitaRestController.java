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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IConsultaDao;
import bad.xcl.models.dao.IUsuarioDao;
import bad.xcl.models.entity.Consulta;
import bad.xcl.models.entity.Enfermedad;
import bad.xcl.models.entity.Usuario;
import bad.xcl.models.services.IConsultaService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/cita")
public class CitaRestController {
	
	//Services.
	@Autowired
	private IConsultaService consultaService;
	
	//Daos.
	@Autowired
	private IConsultaDao consultaDao;
	@Autowired
	private IUsuarioDao usuarioDao;
	
	//Crear una nueva cita.
	@PostMapping("/crear_cita")
	public ResponseEntity<?> create(@RequestBody Consulta consulta) {
		Consulta citaNueva = null;
		Map<String, Object> response  = new HashMap<>();
		
		try {
			consulta.setPeso(null);
			consulta.setTemperatura(null);
			consulta.setEstatura(null);
			consulta.setPresion(null);
			consulta.setRitmo(null);
			consulta.setSintoma(null);
			
			consulta.setId(consultaService.generarId());
			citaNueva = consultaService.save(consulta);
			
		} catch (DataAccessException e) {
			response.put("mensaje","Error al realizar al insertar en la base de datos.");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La cita y su consulta ha sido creado con éxito");
		response.put("estado", citaNueva);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Citas por Doctor.
	@GetMapping("/doctor")
	public List<Consulta> obtenerCitasPorDoctor(@RequestParam("id_doctor") Integer id_doctor, 
								 @RequestParam("id_hospital") Integer id_hospital) {
		List<Consulta> consultas_citas = new ArrayList<Consulta>();
		for (Consulta consulta_cita: consultaDao.obtenerCitasPorDoctor(id_doctor, id_hospital)) {
			consultas_citas.add(consulta_cita);
		}
		return consultas_citas;
	}
	
	//Citas por Paciente.
	@GetMapping("/paciente")
	public List<Consulta> obtenerCitasPorPaciente(@RequestParam("id_paciente") Integer id_paciente, 
								 @RequestParam("id_hospital") Integer id_hospital) {
		List<Consulta> consultas_citas = new ArrayList<Consulta>();
		for (Consulta consulta_cita: consultaDao.obtenerCitasPorPaciente(id_paciente, id_hospital)) {
			consultas_citas.add(consulta_cita);
		}
		return consultas_citas;
	}
	@GetMapping("/doctores/hospital/{id}")
	public List<Usuario> doctoresPorHospital(@PathVariable Integer id){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Usuario usuario : usuarioDao.doctoresPorHospital(id)) {
			usuarios.add(usuario);		
		}
		return usuarios;
	}
	
	//Eliminar fisicamente la cita. Si la consulta con los demas valores son nulos.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		Consulta cita = null;
		Map<String, Object> response  = new HashMap<>();
		try {
			cita = consultaService.findById(id);
			
			if(cita == null) {
				response.put("mensaje", "La cita con ID:" + id + " no existe.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				Double peso = cita.getPeso();
				Double temp = cita.getTemperatura();
				Double estatura = cita.getEstatura();
				String presion = cita.getPresion();
				Integer ritmo = cita.getRitmo();
				String sintoma = cita.getSintoma();
				Enfermedad enf = cita.getEnfermedad();
				
				if (peso == null && temp == null && estatura == null && presion == null && ritmo == null && sintoma == null && enf == null) {
					try {
						consultaDao.deleteById(id);
					} catch (DataAccessException e) {
						response.put("mensaje","Error al eliminar en la base de datos.");
						response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
						return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					response.put("mensaje", "Cita eliminada con éxito.");
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
				} else {
					response.put("mensaje", "No se puede eliminar una consulta.");
					return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
				}
			
			}
			
		}  catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//response.put("mensaje", "La cita ha sido eliminado con éxito");
		//return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
	}
	

}
