package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Enfermedad;

public interface IEnfermedadDao extends CrudRepository<Enfermedad,Integer> {
	public List<Enfermedad> findAllByTipoEnfermedad_Id(int id);

}
