package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Especialidad;
import bad.xcl.models.entity.EstadoCivil;

public interface IEspecialidadService {
	public Especialidad findById(int id);
	public void delete(int id);
	
	public List<Especialidad> findAll();
	
	public Especialidad save(Especialidad especialidad);
	
	public Especialidad guardarEspecialidad(Especialidad especialidad);
	
	public List<Especialidad> listarActivos();
	
	public List<Especialidad> listarEspecialidad(Integer id);
	
	//Genera los Ids para una secuencia
	public int generarId();
	
	public Especialidad verEspecialidad(int id);

	public void eliminarEspecialidad(int id);
}

