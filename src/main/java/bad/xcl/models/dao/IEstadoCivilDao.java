package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.EstadoCivil;
import bad.xcl.models.entity.Rol;

public interface IEstadoCivilDao extends CrudRepository<EstadoCivil, Integer> {
	public EstadoCivil findFirstByOrderByIdDesc();
}
