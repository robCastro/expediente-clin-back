package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Integer> {
	public Rol findFirstByOrderByIdDesc();
}
