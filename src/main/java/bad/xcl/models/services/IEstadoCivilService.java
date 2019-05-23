package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.EstadoCivil;

public interface IEstadoCivilService {
	
	public List<EstadoCivil> listar();
	
	public List<EstadoCivil> listarActivos();
	
	public EstadoCivil findById(Integer id);
	
	public EstadoCivil save(EstadoCivil estadoCivil);
	
	public void delete(Integer id);
	
	public int generarId();

}
