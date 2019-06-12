package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Consulta;

public interface IConsultaDao extends CrudRepository<Consulta, Integer>{

	public Consulta findFirstByOrderByIdDesc();
}
