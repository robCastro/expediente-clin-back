package bad.xcl.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bad.xcl.models.dao.IUsuarioDao;
import bad.xcl.models.entity.Hospital;
import bad.xcl.models.entity.Usuario;
import bad.xcl.models.services.IUsuarioService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	@GetMapping("/todos")
	public List<Usuario> index(){
		//return usuarioService.listar();
		return usuarioDao.listarRaw();
	}
	
	@GetMapping("/actual")
	public Usuario usuarioActual() {
		String usernameActual = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return usuarioService.usuarioPorUsername(usernameActual);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> show(@PathVariable Integer id) {
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		try {
			usuario = usuarioService.findById(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(usuario == null) {
			response.put("mensaje", "El usuario " + id + " no existe.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		Usuario usuarioNew = null;
		usuario.setEnabled(true);
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioNew = usuarioService.guardar(usuario);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con exito");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
		
	@PostMapping("/crearPaciente")
	public ResponseEntity<?> create_inactivo(@RequestBody Usuario usuario) {
		Usuario usuarioNew = null;
		usuario.setEnabled(true);
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioNew = usuarioService.guardar(usuario);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido creado con exito");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	
	
	
	//No actualiza username ni contrasenia
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioActual = usuarioService.findById(id);
		if(usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar, el usuario ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Usuario usuarioActualizado = null;
		usuarioActual.setApellidos(usuario.getApellidos());
		usuarioActual.setDetalle(usuario.getDetalle());
		usuarioActual.setEmail(usuario.getEmail());
		usuarioActual.setEnabled(usuario.getEnabled());
		usuarioActual.setEspecialidad(usuario.getEspecialidad());
		usuarioActual.setEstadoCivil(usuario.getEstadoCivil());
		usuarioActual.setFecha(usuario.getFecha());
		usuarioActual.setGenero(usuario.getGenero());
		usuarioActual.setHospital(usuario.getHospital());
		usuarioActual.setMunicipio(usuario.getMunicipio());
		usuarioActual.setNombres(usuario.getNombres());
		usuarioActual.setPais(usuario.getPais());
		usuarioActual.setRoles(usuario.getRoles());
		usuarioActual.setTelefono(usuario.getTelefono());
		try {
			usuarioActualizado = usuarioService.save(usuarioActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el estado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido actualizado con exito");
		response.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			usuarioService.eliminar(id);
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el usuario de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//Habilitar usuario
	@PutMapping("habilitar/{id}")
	public ResponseEntity<?> habilitarUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioActual = usuarioService.findById(id);
		if(usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo habilitar, el usuario ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Usuario usuarioActualizado = null;
		usuarioActual.setEnabled(true);
		try {
			usuarioActualizado = usuarioService.save(usuarioActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al habilitar el estado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido habilitado con exito");
		response.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	//Habilitar usuario
	@PutMapping("deshabilitar/{id}")
	public ResponseEntity<?> deshabilitarUsuario(@RequestBody Usuario usuario, @PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioActual = usuarioService.findById(id);
		if(usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo deshabilitar, el usuario ".concat(id.toString()).concat(" no existe"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		Usuario usuarioActualizado = null;
		usuarioActual.setEnabled(false);
		try {
			usuarioActualizado = usuarioService.save(usuarioActual);			
		}
		catch(DataAccessException e) {
			response.put("mensaje", "Error al deshabilitar el estado en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El usuario ha sido deshabilitado con exito");
		response.put("usuario", usuarioActualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED); 
	}
	
	
	//Usuarios habilitados de un hospital especifico.
	@GetMapping("habilitado/hospital/{id}")
	public List<Usuario> usuariosHabilitadosPorHospital(@PathVariable Integer id){
		List<Usuario> usuarios = new ArrayList<Usuario>();			
		for (Usuario usuario : usuarioDao.usuariosHabilitadosPorHospital(id)) {
			usuarios.add(usuario);		
		}
		return usuarios;
	}
	
	//Usuarios deshabilitados de un hospital especifico.
	@GetMapping("deshabilitado/hospital/{id}")
	public List<Usuario> usuariosDeshabilitadosPorHospital(@PathVariable Integer id){
		List<Usuario> usuarios = new ArrayList<Usuario>();			
		for (Usuario usuario : usuarioDao.usuariosDeshabilitadosPorHospital(id)) {
			usuarios.add(usuario);		
		}	
		return usuarios;
	}

	//Usuarios bloqueados de un hospital especifico.
	@GetMapping("bloqueado/hospital/{id}")
	public List<Usuario> usuariosBloqueadosPorHospital(@PathVariable Integer id){
		List<Usuario> usuarios = new ArrayList<Usuario>();			
		for (Usuario usuario : usuarioDao.usuariosBloqueadosPorHospital(id)) {
			usuarios.add(usuario);		
		}	
		return usuarios;
	}
	

	
}
