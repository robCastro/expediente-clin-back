package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Municipio;

public interface IMunicipioService {
	public List<Municipio> listar(int id);
	public Municipio findById(int id);

}
