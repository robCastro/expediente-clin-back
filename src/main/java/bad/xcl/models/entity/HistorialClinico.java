package bad.xcl.models.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historial_clinico")
public class HistorialClinico implements Serializable{
	@Id
	@Column(name = "id_historial")
	private Integer id;
	@Column(name = "fecha_historial")
	private Date fecha;
	@Column(name = "activo_historial", nullable = false)
	private Boolean activo;
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_enfermedad", nullable = false)
	private Enfermedad enfermedad;
	
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public Boolean getActivo() {
		return activo;
	}



	public void setActivo(Boolean activo) {
		this.activo = activo;
	}



	public Enfermedad getEnfermedad() {
		return enfermedad;
	}



	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}



	public Paciente getPaciente() {
		return paciente;
	}



	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}



	private static final long serialVersionUID = 1L;

}
