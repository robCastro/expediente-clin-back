package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Usuario;

public interface IUsuarioService {
	
	public Integer generarId();
	
	public Usuario guardar(Usuario usuario);
	
	public Usuario save(Usuario usuario);
	
	public void eliminar(Integer id);
	
	public Usuario findById(Integer id);
	
	public List<Usuario> listar();
	
	public String generarUsuario(String apellido);
	
	public Usuario usuarioPorUsername(String username);
	
}
