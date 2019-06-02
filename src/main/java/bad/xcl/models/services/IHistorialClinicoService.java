package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.HistorialClinico;

public interface IHistorialClinicoService {
	
	public Integer generarId();
	
	public HistorialClinico guardar(HistorialClinico historial);
	
	public void eliminar(Integer id);
	
	public List<HistorialClinico> listarPorPaciente(Integer id_paciente);

}
