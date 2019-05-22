package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Especialidad;

public interface IEspecialidadService {

	public Especialidad save(Especialidad especialidad);
	
	public Especialidad guardar(Especialidad especialidad);
	
	public List<Especialidad> listar();
	
	public List<Especialidad> listarActivos();
	
	public void eliminar(Integer id);
	
	public Integer generarId();
	
	public Especialidad findById(Integer id);
}
