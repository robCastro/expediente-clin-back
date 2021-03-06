package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByUsername(String username);
	public Usuario findFirstByOrderByIdDesc();
	

	@Query(
		value = "select * from usuario u where u.enabled = 1 and u.id_usuario not in(select id_usuario from users_authorities uu where uu.id_rol in (1,2))",
		nativeQuery = true
	)
	public List<Usuario> listarRaw();
	
	@Query(
			value = "select * from usuario u where u.enabled = 1 and u.id_usuario not in(select id_usuario from users_authorities uu where uu.id_rol in (1,2,6)) \r\n" + 
					"and id_hospital = (select id_hospital from hospital where id_hospital = ?1)",
			nativeQuery = true
		)
	public List<Usuario> usuariosHabilitadosPorHospital(Integer id_hospital);	

	@Query(
			value = "select * from usuario u where u.enabled = 0 and u.id_usuario not in(select id_usuario from users_authorities uu where uu.id_rol in (1,2,6)) \r\n" + 
					"and id_hospital = (select id_hospital from hospital where id_hospital = ?1)",
			nativeQuery = true
		)
	public List<Usuario> usuariosDeshabilitadosPorHospital(Integer id_hospital);
	
	@Query(
			value = "select * from usuario u where u.enabled is null and u.id_usuario not in(select id_usuario from users_authorities uu where uu.id_rol in (1,2,6)) \r\n" + 
					"and id_hospital = (select id_hospital from hospital where id_hospital = ?1)",
			nativeQuery = true
		)
	public List<Usuario> usuariosBloqueadosPorHospital(Integer id_hospital);
	
	@Query(
		value = "select * from usuario join (select * from hospital where aprobado_hospital = ?1 and activo_hospital = 1) h on (usuario.id_hospital = h.id_hospital) where id_usuario in (\r\n" + 
				"select id_usuario from usuario natural join users_authorities natural join rol where nombre_rol like ?2)",
		nativeQuery = true
	)
	public List<Usuario> listarUsuarioPorHospital(Integer aprobado, String nombre);

	//Lista de hospitales por sus respectivos Administradores de Hospital con aprobado_hospital is null.
	@Query(
		value = "select * from usuario join (select * from hospital where aprobado_hospital is null and activo_hospital = 1) h on (usuario.id_hospital = h.id_hospital) where id_usuario in (\r\n" + 
					"select id_usuario from usuario natural join users_authorities natural join rol where nombre_rol like ?1)",
		nativeQuery = true
	)
	public List<Usuario> listarHospitalesPendientes(String nombre);
	
	public List<Usuario> findAllByHospital_Aprobado(boolean enabled);
	
	
	@Query(
			value="select count(*) from usuario u where u.username like ?1%",
			nativeQuery = true
		)
		public 
	Integer generarUser(String apellidosUsuario);
	
	
	@Query(
			value = "select * from usuario u where u.enabled = 1 and u.id_usuario in(select id_usuario from users_authorities uu where uu.id_rol = 3 ) \r\n" + 
					"and id_hospital = (select id_hospital from hospital where id_hospital = ?1)",
			nativeQuery = true
		)
	public List<Usuario> doctoresPorHospital(Integer id_hospital);
	
}

	

