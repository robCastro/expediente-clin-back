package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Consulta;
import bad.xcl.models.entity.Usuario;

public interface IConsultaDao extends CrudRepository<Consulta, Integer>{

	public Consulta findFirstByOrderByIdDesc();
	
	@Query(
			value = "select * from consulta where id_usuario in (select id_usuario from usuario where enabled = 1 and id_usuario = ?1 and id_usuario in(select id_usuario from users_authorities uu \r\n" + 
					"where uu.id_rol = 3) and id_hospital = (select id_hospital from hospital where id_hospital = ?2))",
			nativeQuery = true
		)
	public List<Consulta> obtenerCitasPorDoctor(Integer id_doctor, Integer id_hospital);
	@Query(
			value = "select * from consulta where id_paciente in \r\n" + 
					"(select id_paciente from paciente natural join usuario \r\n" + 
					"where id_paciente = ?1 and enabled=1 and id_usuario in(select id_usuario from users_authorities uu \r\n" + 
					"where uu.id_rol = 6) and id_hospital = (select id_hospital from hospital where id_hospital = ?2))",
			nativeQuery = true
		)
	public List<Consulta> obtenerCitasPorPaciente(Integer id_paciente, Integer id_hospital);	
	
	@Query(
			value = "select * from consulta where id_usuario in (select id_usuario from usuario where enabled = 1 \r\n" + 
					"and id_hospital in (select id_hospital from usuario where id_hospital = ?1))\r\n" + 
					"and ((TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy') > TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy'))\r\n" + 
					"OR (TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy')>= (TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy')) AND (hora_consulta > to_char(systimestamp,'hh24'))))",  
			nativeQuery = true
		)
	public List<Consulta> obtenerCitasPendientes(Integer id_hospital);
	
	@Query(
			value = "select * from consulta where id_usuario in (select id_usuario from usuario where enabled = 1 \r\n" + 
					"and id_hospital in (select id_hospital from usuario where id_hospital = ?1))\r\n" + 
					"and ((TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy') < TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy'))\r\n" + 
					"OR (TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy')<= (TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy')) AND (hora_consulta < to_char(systimestamp,'hh24'))))",
			nativeQuery = true
		)
	public List<Consulta> obtenerCitasPasadas(Integer id_hospital);
	
	@Query(
			value = "select * from consulta where id_usuario in (select id_usuario from usuario where enabled = 1 and id_usuario = ?1\r\n" + 
					"and id_usuario in(select id_usuario from users_authorities uu where uu.id_rol = 3))\r\n" + 
					"and ((TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy') > (TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy')))\r\n" + 
					"OR (TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy') >= (TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy')) AND hora_consulta > to_char(systimestamp,'hh24')))",
			nativeQuery = true
		)
	public List<Consulta> obtenerCitasPendientesDoctor(Integer id_usuario);
	
	@Query(
			value = "select * from consulta where id_usuario in (select id_usuario from usuario where enabled = 1 and id_usuario = ?1\r\n" + 
					"and id_usuario in(select id_usuario from users_authorities uu where uu.id_rol = 3))\r\n" + 
					"and ((TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy') < (TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy')))\r\n" + 
					"OR (TO_CHAR(TO_DATE(fecha_consulta),'ddmmyyyy') <= (TO_CHAR(TO_DATE(SYSDATE),'ddmmyyyy')) AND hora_consulta < to_char(systimestamp,'hh24')))",
			nativeQuery = true
		)
	public List<Consulta> obtenerCitasPasadasDoctor(Integer id_usuario);

}

