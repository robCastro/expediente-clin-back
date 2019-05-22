package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Genero;

public interface IGeneroService {

	public Genero save(Genero genero);
	
	public Genero guardar(Genero genero);
	
	public List<Genero> listar();
	
	public List<Genero> listarActivos();
	
	public void eliminar(Integer id);
	
	public Integer generarId();
	
	public Genero findById(Integer id);

}
