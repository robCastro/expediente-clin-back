package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.EstadoCivil;
import bad.xcl.models.entity.Rol;

public interface IEstadoCivilDao extends CrudRepository<EstadoCivil, Integer> {
	public EstadoCivil findFirstByOrderByIdDesc();
	public List<EstadoCivil> findAllByActivo(boolean activo);
}
