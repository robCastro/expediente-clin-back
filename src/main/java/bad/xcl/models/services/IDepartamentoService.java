package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Departamento;

public interface IDepartamentoService {
	public List<Departamento> listar();
	
	public Departamento findById(int id);

}
