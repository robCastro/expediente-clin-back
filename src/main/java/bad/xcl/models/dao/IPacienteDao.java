package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Paciente;

public interface IPacienteDao extends CrudRepository<Paciente, Integer>{
	
	public Paciente findFirstByOrderByIdDesc();
	
	@Query(
			value = "select * from paciente where id_usuario in (select id_usuario from usuario u where u.enabled = 1 and u.id_usuario not in(select id_usuario from usuarios_roles uu where uu.id_rol in (1,2)) \r\n" + 
					"and id_hospital = (select id_hospital from hospital where id_hospital = ?1))",
			nativeQuery = true
		)
	public List<Paciente> listarPacientesPorHospital(Integer id_hospital);

}
