package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Departamento;
import bad.xcl.models.entity.Hospital;

public interface IHospitalDao extends CrudRepository<Hospital, Integer> {
	
	public List<Hospital> findAllByAprobado(boolean aprobado);
	
	public Hospital findFirstByOrderByIdDesc();

	@Query(
		value = "select * from hospital h where aprobado_hospital is null",
		nativeQuery = true
	)
	public List<Hospital> findAllPendiente();
	
	@Query(
			value = "select id_departamento from departamento where id_departamento = \r\n" + 
					"			(select id_departamento from municipio where municipio.id_municipio in (select id_municipio from hospital where id_hospital=?1))",
			nativeQuery = true
		)
	public Integer departamentoDelHospital(Integer id_hospital);

}
