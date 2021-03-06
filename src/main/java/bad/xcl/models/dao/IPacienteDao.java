package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Paciente;

public interface IPacienteDao extends CrudRepository<Paciente, Integer>{
	
	public Paciente findFirstByOrderByIdDesc();
	
	//Paciente habilitados (enabled = 1) o deshabilitados (enabled = 0) de un hospital especifico.
	@Query(
			value = "select * from paciente where id_usuario in (select id_usuario from usuario u where u.enabled = ?1 and u.id_usuario not in(select id_usuario from users_authorities uu where uu.id_rol in (1,2)) and id_hospital = \r\n" + 
					"(select id_hospital from hospital where id_hospital = ?2))",
			nativeQuery = true
		)
	public List<Paciente> listarPacientesPorHospital(Integer enabled, Integer id_hospital);

	//Paciente bloqueados de un hospital especifico.
	@Query(
			value = "select * from paciente where id_usuario in (select id_usuario from usuario u where u.enabled is null and u.id_usuario not in(select id_usuario from users_authorities uu where uu.id_rol in (1,2)) and id_hospital = \r\n" + 
					"(select id_hospital from hospital where id_hospital = ?1))",
			nativeQuery = true
		)
	public List<Paciente> listarPacientesBloqueadosPorHospital(Integer id_hospital);
	
	@Query(
			value = "select * from paciente_basico where id_hospital=?1",
			nativeQuery = true
		)
	public List<Object> listarPacientesBasicos(Integer id_hospital);
	
	//Obtener paciente apartir del usuario
	@Query(
			value = "select * from paciente where id_usuario = ?1 and id_usuario in \r\n" + 
					"(select id_usuario from usuario natural join users_authorities natural join rol where id_rol = 6)",
			nativeQuery = true
		)
	public Paciente obtenerPacienteApartirDelUsuario(Integer id_usuario);
}
