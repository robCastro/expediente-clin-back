package bad.xcl.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tratamiento")
public class Tratamiento implements Serializable {
	@Id
	@Column(name = "id_tratamiento")
	private Integer id;
	
	@Column(name = "nombre_tratamiento", nullable = false)
	private String nombre;
	
	@Column(name = "fecha_inicio_tra", nullable = false)
	private Date fechaInicio;
	
	@Column(name = "fecha_fin_tra", nullable = false)
	private Date fechaFin;
	
	@Column(name = "frecuencia", nullable = false)
	private String frecuencia;
	
	@Column(name = "dosis", nullable = false)
	private String dosis;
	
	
	//Fks
	
	@ManyToOne
	@JoinColumn(name = "id_consulta")
	private Consulta consulta;

	

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


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getFrecuencia() {
		return frecuencia;
	}


	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}


	public String getDosis() {
		return dosis;
	}


	public void setDosis(String dosis) {
		this.dosis = dosis;
	}


	public Consulta getConsulta() {
		return consulta;
	}


	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	private static final long serialVersionUID = 1L;
}
