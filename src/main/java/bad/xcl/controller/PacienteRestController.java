package bad.xcl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
