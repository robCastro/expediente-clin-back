package bad.xcl.models.services;

import bad.xcl.models.entity.Consulta;

public interface IConsultaService {
	
	public Consulta save(Consulta consulta);
	
	public Consulta findById(Integer id);
	
	public int generarId();
	
	public void delete(Integer id);
}
