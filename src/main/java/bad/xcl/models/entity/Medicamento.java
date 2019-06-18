package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medicamento")
public class Medicamento implements Serializable {
	
	@Id
	@Column(name = "id_medicamento")
	private Integer id;
	@Column(name = "nombre_medicamento", length = 128, nullable = false)
	private String nombre;
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_grupo_ter")
	private GrupoTerapeutico grupoTerapeutico;
	
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
	public GrupoTerapeutico getGrupoTerapeutico() {
		return grupoTerapeutico;
	}
	public void setGrupoTerapeutico(GrupoTerapeutico grupoTerapeutico) {
		this.grupoTerapeutico = grupoTerapeutico;
	}


	private static final long serialVersionUID = 1L;
	
	
	
}
