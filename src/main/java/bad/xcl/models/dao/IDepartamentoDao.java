package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Departamento;

public interface IDepartamentoDao extends CrudRepository<Departamento, Integer>{
	public Departamento findFirstByOrderByIdDesc();

}
