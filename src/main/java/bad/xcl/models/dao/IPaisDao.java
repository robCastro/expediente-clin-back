package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Pais;

public interface IPaisDao extends CrudRepository<Pais,Integer>{
	public Pais findFirstByOrderByIdDesc();

}
