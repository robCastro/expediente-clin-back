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
@Table(name = "hospital")
public class Hospital implements Serializable {

	@Id
	@Column(name = "id_hospital")
	private Integer id;
	@Column(name = "nombre_hospital", length = 128, nullable = false)
	private String nombre;
	@Column(name = "fecha_registro", nullable = false)
	private Date fecha;
	@Column(name = "telefono_hospital", length = 15)
	private String telefono;
	@Column(name = "detalle_dir_hos", length = 256)
	private String detalle;
	@Column(name = "aprobado_hospital")
	private Boolean aprobado;
	@Column(name = "activo_hospital", nullable = false)
	private Boolean activo;
	
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_pais", nullable = false)
	private Pais pais;
	
	@ManyToOne
	@JoinColumn(name = "id_municipio", nullable = true)
	private Municipio municipio;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	private static final long serialVersionUID = 1L;
}
