package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado_civil")
public class EstadoCivil implements Serializable {

	@Id
	@Column(name = "id_estado_c")
	private Integer id;
	@Column(name = "nombre_estado_c", length = 128, nullable = false)
	private String nombre;
	@Column(name = "activo_estado_c", nullable = false)
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
