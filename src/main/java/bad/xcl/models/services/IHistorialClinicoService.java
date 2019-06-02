package bad.xcl.models.services;

import bad.xcl.models.entity.HistorialClinico;

public interface IHistorialClinicoService {
	
	public Integer generarId();
	
	public HistorialClinico findById(Integer id);
	
	public HistorialClinico save(HistorialClinico historial);

}
