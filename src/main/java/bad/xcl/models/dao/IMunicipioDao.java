package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Municipio;

public interface IMunicipioDao extends CrudRepository<Municipio,Integer> {
	public List<Municipio> findAllByDepartamento_Id(int id);

}
