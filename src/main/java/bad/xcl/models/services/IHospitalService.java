package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Hospital;

public interface IHospitalService  {
	
	/* Lista de Hospitales Aprobados */
	public List<Hospital> listarAprobados();
	
	/* Lista de Hospitales Pendientes */
	public List<Hospital> listarPendientes();
	
	public List<Hospital> findAll();
	
	public Hospital findById(Integer id);
	
	public Hospital save(Hospital hospital);
	
	public void delete(Integer id);
	
	public int generarId();

}
