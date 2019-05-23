package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
	
	public Usuario findByUsername(String username);
	public Usuario findFirstByOrderByIdDesc();

	@Query(
		value = "select * from usuario u where u.enabled = 1 and u.id_usuario not in(select id_usuario from usuarios_roles uu where uu.id_rol in (1,2))",
		nativeQuery = true
	)
	public List<Usuario> listarRaw();
}
