package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bad.xcl.models.dao.IPacienteDao;
import bad.xcl.models.entity.Paciente;

@Service
public class PacienteServiceImpl implements IPacienteService {
	@Autowired
	private IPacienteDao pacienteDao;

	@Override
	public int generarId() {
		try {
			//Obtiene el id mayor y le agrega uno
			return pacienteDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		//En caso que NO hayan registros en la tabla
		catch(NullPointerException e) {
			return 1;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Paciente> findAll() {
		return (List<Paciente>) pacienteDao.findAll();
	}


	@Override
	@Transactional(readOnly=true)
	public Paciente findById(Integer id) {
		return pacienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Paciente save(Paciente paciente) {
		return pacienteDao.save(paciente);
	}

	@Override
	public void delete(int id) {
		pacienteDao.deleteById(id);
	}

}
