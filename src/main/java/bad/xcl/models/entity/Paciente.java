package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
	
	@Id
	@Column(name = "id_paciente")
	private Integer id;
	@Column(name = "nombres_emergencia", length = 128, nullable = false)
	private String nombres;
	@Column(name = "apellidos_emergencia", length = 128, nullable = false)
	private String apellidos;
	@Column(name = "telefono_emergencia", length = 15)
	private String telefono;
	@Column(name = "activo", nullable = true)
	private Boolean activo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", unique=true , nullable=true) //Permite nulos para usuario
	private Usuario usuario;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	private static final long serialVersionUID = 1L;
	
}
