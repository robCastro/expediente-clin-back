package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Tratamiento;

public interface ITratamientoDao extends CrudRepository<Tratamiento, Integer> {
	public Tratamiento findFirstByOrderByIdDesc();
	
	@Query(
			value = "select * from tratamiento t where t.id_consulta = ?1",
			nativeQuery = true
		)
	public List<Tratamiento> listarPorConsulta(Integer id_consulta);

}
