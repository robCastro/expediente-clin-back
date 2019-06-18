package bad.xcl.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import bad.xcl.models.entity.Medicamento;

public interface IMedicamentoDAO extends CrudRepository<Medicamento,Integer> {
	public List<Medicamento> findAllByGrupoTerapeutico_Id(int id);
}
