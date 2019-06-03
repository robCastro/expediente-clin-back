package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.ITipoEnfermedadDao;
import bad.xcl.models.entity.TipoEnfermedad;

@Service
public class TipoEnfermedadServiceImpl implements ITipoEnfermedadService{
	
	@Autowired
	private ITipoEnfermedadDao tipoEnfermedadDao;
	
	@Override
	public List<TipoEnfermedad> listar() {
		
		return (List<TipoEnfermedad>) tipoEnfermedadDao.findAll();
	}

}
