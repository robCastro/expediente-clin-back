package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Tratamiento;

public interface ITratamientoService {
	
	public Integer generarId();
	
	public Tratamiento guardar(Tratamiento tratamiento);
	
	public void delete(int id);
	
	public List<Tratamiento> listarPorConsulta(Integer id_consulta);

}
