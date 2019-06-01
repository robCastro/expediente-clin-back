package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Paciente;


public interface IPacienteService {
	public int generarId();
	
	public List<Paciente> findAll();
	
	public Paciente findById(Integer id);
	
	public Paciente save(Paciente paciente);
	
	public void delete(int id);

}
