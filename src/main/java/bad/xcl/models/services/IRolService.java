package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Rol;

public interface IRolService {
	
	public Rol save(Rol rol);
	
	public Rol guardar(Rol rol);
	
	public List<Rol> listar();
	
	public void eliminar(Integer id);
	
	public Integer generarId();
	
	public Rol findById(Integer id);
}
