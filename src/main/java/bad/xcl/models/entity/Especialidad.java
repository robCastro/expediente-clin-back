package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "especialidad")
public class Especialidad implements Serializable {
	
	@Id
	@Column(name = "id_especialidad")
	private Integer id;
	@Column(name = "nombre_especialidad", length = 128, nullable = false)
	private String nombre;
	@Column(name = "activo_especialidad", nullable = false)
	private Boolean activo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	private static final long serialVersionUID = 1L;
}
