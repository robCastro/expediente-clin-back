package bad.xcl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.services.IMensajeService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/mensaje")
public class MensajeRestController {
	@Autowired
	private IMensajeService mensajeService;
	
	//Mensaje de Aprobación.
	@PostMapping("/enviar")
	public ResponseEntity<?> enviarMensaje(@RequestParam("mensaje") String mensaje, 
								 @RequestParam("asunto") String asunto,
								 @RequestParam("email") String email) {
		var resultado = false;
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
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
