package bad.xcl.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
