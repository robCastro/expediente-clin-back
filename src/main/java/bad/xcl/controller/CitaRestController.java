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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IConsultaDao;
import bad.xcl.models.entity.Consulta;
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
	
	//Mensaje de Aprobación.
	@GetMapping("/doctor")
	public List<Consulta> obtenerCitasPorDoctor(@RequestParam("id_doctor") Integer id_doctor, 
								 @RequestParam("id_hospital") Integer id_hospital) {
		/*var resultado = false;
		Map<String, Object> response  = new HashMap<>();
		try {
			resultado = mensajeService.sendMsj(mensaje, asunto, email);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al enviar el mensaje");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El mensaje ha sido enviado con éxito");
		response.put("estado", resultado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);*/
		List<Consulta> consultas_citas = new ArrayList<Consulta>();
		for (Consulta consulta_cita: consultaDao.obtenerCitasPorDoctor(id_doctor, id_hospital)) {
			consultas_citas.add(consulta_cita);
		}
		return consultas_citas;
	}
	

}
