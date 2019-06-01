package bad.xcl.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IHistorialClinicoDao;

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

}
