package bad.xcl.models.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {
	@Id
	@Column(name = "id_consulta")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@Column(name = "hora_consulta")
	private Integer hora; //Rangos de 0-23
	
	@Min(value=3, message="El peso minimo es 3")
    @Max(value=200, message="El peso maximo es 200")
	@Column(name = "peso", nullable = true)
	private Double peso;
	
	@Min(value=30, message="La temperatura minima es 30")
    @Max(value=40, message="La temperatura maxima es 40")
	@Column(name = "temperatura", nullable = true)
	private Double temperatura;
	
	@Min(value=40, message="La estatura minima es 40")
    @Max(value=200, message="La estatura maxima es 200")
	@Column(name = "estatura", nullable = true)
	private Double estatura;
	
	@Pattern(regexp = "^1[0-4]{1}[0-9]{1}\\/[5-9]{1}[0-9]{1}$", message="Presion arterial incorrecta")
	@Column(name = "presion_arterial", nullable = true, length = 25)
	private String presion;
	
	@Min(value=40, message="El ritmo cardiaco minimo es 40")
    @Max(value=120, message="El ritmo cardiaco maximo es 120")
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

	@ManyToOne
	@JoinColumn(name = "id_enfermedad", nullable = true)
	private Enfermedad enfermedad;
	
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
	
	public Enfermedad getEnfermedad(){
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad){
		this.enfermedad = enfermedad;
	}
	private static final long serialVersionUID = 1L;
}
