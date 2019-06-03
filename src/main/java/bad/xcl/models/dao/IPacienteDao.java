package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Paciente;

public interface IPacienteDao extends CrudRepository<Paciente, Integer>{
	
	public Paciente findFirstByOrderByIdDesc();
	
	//Paciente habilitados (enabled = 1) o deshabilitados (enabled = 0) de un hospital especifico.
	@Query(
			value = "select * from paciente where id_usuario in (select id_usuario from usuario u where u.enabled = ?1 and u.id_usuario not in(select id_usuario from usuarios_roles uu where uu.id_rol in (1,2)) and id_hospital = \r\n" + 
					"(select id_hospital from hospital where id_hospital = ?2))",
			nativeQuery = true
		)
	public List<Paciente> listarPacientesPorHospital(Integer enabled, Integer id_hospital);

	//Paciente bloqueados de un hospital especifico.
	@Query(
			value = "select * from paciente where id_usuario in (select id_usuario from usuario u where u.enabled is null and u.id_usuario not in(select id_usuario from usuarios_roles uu where uu.id_rol in (1,2)) and id_hospital = \r\n" + 
					"(select id_hospital from hospital where id_hospital = ?1))",
			nativeQuery = true
		)
	public List<Paciente> listarPacientesBloqueadosPorHospital(Integer id_hospital);
	

}
