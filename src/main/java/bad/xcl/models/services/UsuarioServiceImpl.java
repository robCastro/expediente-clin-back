package bad.xcl.models.services;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IUsuarioDao;
import bad.xcl.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	public Integer generarId() {
		try {
			return usuarioDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		catch (NullPointerException e) {
			return 1;
		}
	}
	
	@Override
	public Usuario guardar(Usuario usuario) {
		usuario.setId(this.generarId());	
		usuario.setUsername(this.generarUsuario(usuario.getApellidos()));
		SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
		String contra= formato.format(usuario.getFecha());
		usuario.setPassword(contra);
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public Usuario findById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}
	
	@Override
	public void eliminar(Integer id) {
		Usuario usuario = usuarioDao.findById(id).orElse(null);
		if(usuario != null) {
			usuario.setEnabled(false);
			usuarioDao.save(usuario);
		}
	}

	@Override
	public List<Usuario> listar() {
		return (List<Usuario>) usuarioDao.findAll();
	}
	
	@Override
	public String generarUsuario(String apellido) {
		apellido=apellido.toLowerCase();
		Integer corre=usuarioDao.generarUser(apellido)+1;
		String user= apellido+Integer.toString(corre);
		System.out.print(Integer.toString(corre));
		
			return user;
		
	}

}
