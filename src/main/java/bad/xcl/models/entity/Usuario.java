package bad.xcl.models.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	@Id
	@Column(name = "id_usuario")
	private Integer id;
	@Column(name = "username", nullable = false, length = 64, unique = true)
	private String username;
	@Column(name = "password", nullable = false, length = 256)
	private String password;
	@Column(name = "enabled", nullable = true)
	private Boolean enabled;
	@Column(name = "nombres_usuario", nullable = false, length = 128)
	private String nombres;
	@Column(name = "apellidos_usuario", nullable = false, length = 128)
	private String apellidos;
	@Column(name = "fecha_nac_usuario")
	private Date fecha;
	@Column(name = "detalle_dir_usuario", length = 512)
	private String detalle;
	@Column(name = "email_usuario", length = 128, unique = true)
	private String email;
	@Column(name = "telefono_usuario", length = 15)
	private String telefono;
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_estado_c")
	private EstadoCivil estadoCivil;
	
	@ManyToOne
	@JoinColumn(name = "id_genero")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name = "id_municipio")
	private Municipio municipio;
	
	@ManyToOne
	@JoinColumn(name = "id_pais", nullable = false)
	private Pais pais;
	
	@ManyToOne
	@JoinColumn(name = "id_hospital") //Permite nulos para admin_general
	private Hospital hospital;
	
	@ManyToOne
	@JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;
	
	
	@ManyToMany
	@JoinTable(
		name = "usuarios_roles",
		joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
		inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
	)
	private List<Rol> roles;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Boolean getEnabled() {
		return enabled;
	}


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}


	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public Municipio getMunicipio() {
		return municipio;
	}


	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}


	public Pais getPais() {
		return pais;
	}


	public void setPais(Pais pais) {
		this.pais = pais;
	}


	public Hospital getHospital() {
		return hospital;
	}


	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}


	public Especialidad getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}


	public List<Rol> getRoles() {
		return roles;
	}


	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	private static final long serialVersionUID = 1L;
}
