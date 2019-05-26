package bad.xcl.models.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Genero;

public interface IGeneroDao extends CrudRepository<Genero, Integer> {

	public List<Genero> findAllByActivo(boolean activo);
	
	public Genero findFirstByOrderByIdDesc();
}
