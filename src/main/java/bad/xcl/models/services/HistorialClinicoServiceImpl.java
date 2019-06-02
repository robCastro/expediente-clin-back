package bad.xcl.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IHistorialClinicoDao;
import bad.xcl.models.entity.HistorialClinico;

@Service
public class HistorialClinicoServiceImpl implements IHistorialClinicoService {
	
	@Autowired
	private IHistorialClinicoDao historialDao;
	
	@Override
	public Integer generarId() {
		try {
			return historialDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		catch (NullPointerException e) {
			return 1;
		}
	}

	@Override
	public HistorialClinico findById(Integer id) {
		return historialDao.findById(id).orElse(null);
	}
	
	@Override
	public HistorialClinico save(HistorialClinico historial) {
		return historialDao.save(historial);
	}
	
	

}
