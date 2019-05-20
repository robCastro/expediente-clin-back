package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Genero;

public interface IGeneroService {
	
	public List<Genero> findAll();
	
	//busca por id
	public Genero findById(int id);
	
	public Genero save(Genero genero);
	
	public Genero guardarGenero(Genero genero);
	
	public List<Genero> listarGeneros();
	
	public void eliminarGenero(int id);
	
	public int generarId();
	
}
