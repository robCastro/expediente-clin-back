package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IDepartamentoDao;
import bad.xcl.models.entity.Departamento;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService{
	
	@Autowired
	private IDepartamentoDao departamentoDao;

	@Override
	public List<Departamento> listar() {
		
		return (List<Departamento>) departamentoDao.findAll();
	}

	@Override
	public Departamento findById(int id) {
		return departamentoDao.findById(id).orElse(null);
	}

}
