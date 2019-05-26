package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IMunicipioDao;
import bad.xcl.models.entity.Municipio;

@Service
public class MunicipioServiceImpl implements IMunicipioService {
	
	@Autowired
	private IMunicipioDao municipioDao;

	@Override
	public List<Municipio> listar(int id) {
		
		return (List<Municipio>) municipioDao.findAllByDepartamento_Id(id);
	}
	
	public  Municipio findById(int id) {
		return municipioDao.findById(id).orElse(null);
	}

}
