package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Especialidad;

public interface IEspecialidadDao extends CrudRepository<Especialidad, Integer>{

	public Especialidad findFirstByOrderByIdDesc();
	public List<Especialidad> findAllByActivo(boolean activo);
}
