package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.HistorialClinico;


public interface IHistorialClinicoDao extends CrudRepository<HistorialClinico, Integer>{
	public HistorialClinico findFirstByOrderByIdDesc();
	
	@Query(
			value = "select * from historial_clinico h where h.activo_historial = 1 and h.id_paciente = ?1",
			nativeQuery = true
		)
	public List<HistorialClinico> listarPorPaciente(Integer id_paciente);

}
