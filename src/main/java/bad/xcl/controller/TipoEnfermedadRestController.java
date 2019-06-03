package bad.xcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.entity.TipoEnfermedad;
import bad.xcl.models.services.ITipoEnfermedadService;



@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
@RequestMapping("/tipoEnfermedad")
public class TipoEnfermedadRestController {

	@Autowired
	private ITipoEnfermedadService tipoEnfermedadService;
	
	@GetMapping("/todos")
	public List<TipoEnfermedad> index(){
		return tipoEnfermedadService.listar();
		
	}
}
