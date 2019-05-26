package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Pais;

public interface IPaisService {
	public List<Pais> listar();
	
	public Pais findById(Integer id);

}
