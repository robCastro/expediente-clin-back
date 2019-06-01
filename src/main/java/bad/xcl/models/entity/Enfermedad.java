package bad.xcl.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enfermedad")
public class Enfermedad implements Serializable{
	@Id
	@Column(name = "id_enfermedad")
	private Integer id;
	@Column(name = "nombre_enfermedad", length = 128, nullable = false)
	private String nombre;
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_tipo_enf")
	private TipoEnfermedad tipoEnfermedad;
	
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

	public TipoEnfermedad getTipoEnfermedad() {
		return tipoEnfermedad;
	}

	public void setTipoEnfermedad(TipoEnfermedad tipoEnfermedad) {
		this.tipoEnfermedad = tipoEnfermedad;
	}

	private static final long serialVersionUID = 1L;
}
