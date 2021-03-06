package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_terapeutico")
public class GrupoTerapeutico implements Serializable{
	
	@Id
	@Column(name = "id_grupo_ter")
	private Integer id;
	@Column(name = "nombre_grupo_ter", length = 128, nullable = false)
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
