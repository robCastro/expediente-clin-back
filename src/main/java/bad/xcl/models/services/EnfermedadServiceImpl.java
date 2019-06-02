package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IEnfermedadDao;
import bad.xcl.models.entity.Enfermedad;

@Service
public class EnfermedadServiceImpl implements IEnfermedadService{
	
	@Autowired
	private IEnfermedadDao enfermedadDao;
	
	@Override
	public List<Enfermedad> listar(int id) {
		
		return (List<Enfermedad>) enfermedadDao.findAllByTipoEnfermedad_Id(id);
	}
	
	public  Enfermedad findById(int id) {
		return enfermedadDao.findById(id).orElse(null);
	}

}
