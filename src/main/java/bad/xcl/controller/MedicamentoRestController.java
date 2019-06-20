package bad.xcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IMedicamentoDAO;
import bad.xcl.models.entity.Enfermedad;
import bad.xcl.models.entity.Medicamento;


@CrossOrigin(origins= {"http://localhost:4200/"})
@RestController
@RequestMapping("/medicamento")
public class MedicamentoRestController {
	
	@Autowired
	private IMedicamentoDAO medicamentoDAO;
	
	@GetMapping("/lista/{id}")
	public List<Medicamento> index(@PathVariable Integer id){
		return (List<Medicamento>) medicamentoDAO.findAllByGrupoTerapeutico_Id(id);
	}

}
