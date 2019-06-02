package bad.xcl.models.dao;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.TipoEnfermedad;


public interface ITipoEnfermedadDao extends CrudRepository<TipoEnfermedad,Integer> {
	public TipoEnfermedad findFirstByOrderByIdDesc();

}
