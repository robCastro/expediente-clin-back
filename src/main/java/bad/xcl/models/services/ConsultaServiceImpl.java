package bad.xcl.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bad.xcl.models.dao.IConsultaDao;
import bad.xcl.models.entity.Consulta;

@Service
public class ConsultaServiceImpl implements IConsultaService{
	
	@Autowired
	private IConsultaDao consultaDao;
	
	@Override
	public Consulta save(Consulta consulta) {
		return consultaDao.save(consulta);
	}
	
	@Override
	public Consulta findById(Integer id) {
		return consultaDao.findById(id).orElse(null);
	}
	
	@Override
	public int generarId() {
		try {
			//Obtiene el id mayor y le agrega uno
			return consultaDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		//En caso que NO hayan registros en la tabla
		catch(NullPointerException e) {
			return 1;
		}
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		consultaDao.deleteById(id);		
	}

}
