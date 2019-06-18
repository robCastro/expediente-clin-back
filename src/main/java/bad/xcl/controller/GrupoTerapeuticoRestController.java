package bad.xcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IGrupoTerapeuticoDAO;
import bad.xcl.models.entity.GrupoTerapeutico;



@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
@RequestMapping("/grupo_tera")
public class GrupoTerapeuticoRestController {
	
	@Autowired
	private IGrupoTerapeuticoDAO grupoTerapeuticoDAO;
	
	@GetMapping("/todos")
	public List<GrupoTerapeutico> index(){
		return (List<GrupoTerapeutico>)grupoTerapeuticoDAO.findAll();
	}

}
