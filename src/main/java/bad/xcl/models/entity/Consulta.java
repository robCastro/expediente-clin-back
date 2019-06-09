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
@Table(name = "consulta")
public class Consulta implements Serializable {
	@Id
	@Column(name = "id_consulta")
	private Integer id;
	
	@Column(name = "fecha_consulta")
	private Date fecha;
	
	@Column(name = "hora_consulta")
	private Integer hora; //Rangos de 0-23
	
	@Column(name = "peso", nullable = true)
	private Double peso;
	
	@Column(name = "temperatura", nullable = true)
	private Double temperatura;
	
	@Column(name = "estatura", nullable = true)
	private Double estatura;
	
	@Column(name = "presion_arterial", nullable = true, length = 25)
	private String presion;
	
	@Column(name = "ritmo_cardiaco", nullable = true)
	private Integer ritmo;
	
	@Column(name = "sintoma", nullable = true, length = 2048)
	private String sintoma;
	
	
	//FKs
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente; //Paciente que recibirá consulta
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario; //Usuario que dará la consulta

	
	
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

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}

	public Double getEstatura() {
		return estatura;
	}

	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}

	public String getPresion() {
		return presion;
	}

	public void setPresion(String presion) {
		this.presion = presion;
	}

	public Integer getRitmo() {
		return ritmo;
	}

	public void setRitmo(Integer ritmo) {
		this.ritmo = ritmo;
	}

	public String getSintoma() {
		return sintoma;
	}

	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	private static final long serialVersionUID = 1L;
}
