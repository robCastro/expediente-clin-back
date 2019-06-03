package bad.xcl.models.services;

import java.util.List;

import bad.xcl.models.entity.Enfermedad;

public interface IEnfermedadService {
	public List<Enfermedad> listar(int id);
	public Enfermedad findById(int id);

}
