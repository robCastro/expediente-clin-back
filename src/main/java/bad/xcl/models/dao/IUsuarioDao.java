package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Integer> {
	
	public Usuario findFirstByOrderByIdDesc();
	
}
