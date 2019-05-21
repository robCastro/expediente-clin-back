package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bad.xcl.models.dao.IHospitalDao;
import bad.xcl.models.entity.Hospital;

@Service
public class HospitalServiceImpl implements IHospitalService {

	@Autowired
	private IHospitalDao hospitalDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Hospital> listarAprobados() {
		return hospitalDao.findAllByAprobado(true);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Hospital> listarPendientes() {
		return hospitalDao.findAllByAprobado(false);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Hospital> findAll() {
		return (List<Hospital>) hospitalDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Hospital findById(Integer id) {
		return hospitalDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Hospital save(Hospital hospital) {
		return hospitalDao.save(hospital);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		hospitalDao.deleteById(id);
	}
	
	@Override
	public int generarId() {
		try {
			//Obtiene el id mayor y le agrega uno
			return hospitalDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		//En caso que NO hayan registros en la tabla
		catch(NullPointerException e) {
			return 1;
		}
	}

}
