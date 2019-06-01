package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.HistorialClinico;


public interface IHistorialClinicoDao extends CrudRepository<HistorialClinico, Integer>{
	public HistorialClinico findFirstByOrderByIdDesc();

}
