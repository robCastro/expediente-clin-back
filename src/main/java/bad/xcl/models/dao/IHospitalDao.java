package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Hospital;

public interface IHospitalDao extends CrudRepository<Hospital, Integer> {
	public List<Hospital> findAllByAprobado(boolean aprobado);
	public Hospital findFirstByOrderByIdDesc();
}
