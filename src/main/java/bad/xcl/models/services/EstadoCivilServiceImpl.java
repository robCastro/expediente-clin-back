package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bad.xcl.models.dao.IEstadoCivilDao;
import bad.xcl.models.entity.EstadoCivil;
import bad.xcl.models.entity.Rol;

@Service
public class EstadoCivilServiceImpl implements IEstadoCivilService {

	@Autowired
	private IEstadoCivilDao estadoCivilDao;

	@Override
	@Transactional(readOnly = true)
	public List<EstadoCivil> listar() {
		return (List<EstadoCivil>) estadoCivilDao.findAll();
	}
	
	@Override
	public EstadoCivil findById(Integer id) {
		return estadoCivilDao.findById(id).orElse(null);
	}

	
	@Override
	@Transactional
	public EstadoCivil save(EstadoCivil estadoCivil) {
		return estadoCivilDao.save(estadoCivil);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		estadoCivilDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public int generarId() {
		try {
			//Obtiene el id mayor y le agrega uno
			return estadoCivilDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		//En caso que NO hayan registros en la tabla
		catch(NullPointerException e) {
			return 1;
		}
	}


}
