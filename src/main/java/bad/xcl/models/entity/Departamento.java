package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

	@Id
	@Column(name = "id_departamento")
	private Integer id;
	@Column(name = "nombre_departamento", length = 128, nullable = false)
	private String nombre;
	
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
	
	private static final long serialVersionUID = 1L;
}
