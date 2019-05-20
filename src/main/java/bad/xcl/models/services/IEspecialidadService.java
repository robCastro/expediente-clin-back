package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Especialidad;

public interface IEspecialidadService {
	public Especialidad findById(int id);
	public void delete(int id);
	
	public List<Especialidad> findAll();
	
	//Para actualizar
	public Especialidad save(Especialidad especialidad);
	
	//Para guardar, genera PK
	public Especialidad guardarEspecialidad(Especialidad especialidad);
	
	public List<Especialidad> listarEspecialidad(Integer id);
	
	//Genera los Ids para una secuencia
	public int generarId();
	
	public Especialidad verEspecialidad(int id);

	public void eliminarEspecialidad(int id);
}

