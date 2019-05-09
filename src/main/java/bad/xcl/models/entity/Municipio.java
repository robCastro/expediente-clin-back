package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "municipio")
public class Municipio implements Serializable {
	
	@Id
	@Column(name = "id_municipio")
	private Integer id;
	@Column(name = "nombre_municipio", length = 128, nullable = false)
	private String nombre;
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_departamento")
	private Departamento departamento;

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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	private static final long serialVersionUID = 1L;
}
