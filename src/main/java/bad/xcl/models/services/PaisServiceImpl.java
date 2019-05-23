package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IPaisDao;
import bad.xcl.models.entity.Pais;

@Service
public class PaisServiceImpl implements IPaisService{
	
	@Autowired
	private IPaisDao paisDao;
	
	@Override
	public List<Pais> listar() {
		
		return (List<Pais>) paisDao.findAll();
	}

	@Override
	public Pais findById(Integer id) {
		
		return paisDao.findById(id).orElse(null);
	}

}
