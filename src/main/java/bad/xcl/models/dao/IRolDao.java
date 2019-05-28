package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Integer> {
	public Rol findFirstByOrderByIdDesc();
	
	@Query(
			value = "SELECT r.ID_ROL, SUBSTR(r.NOMBRE_ROL, 6) as nombre_rol FROM ROL r where r.ID_ROL NOT IN (1,2)",
			nativeQuery = true
	)
	public List<Rol> listarRaw();
	
	public Rol findByNombre(String nombre);
	
}
