package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bad.xcl.models.dao.IEspecialidadDao;
import bad.xcl.models.entity.Especialidad;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired
	private IEspecialidadDao especialidadDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Especialidad> findAll() {
		return (List<Especialidad>) especialidadDao.findAll();
	}
	
	@Override
	public Especialidad save(Especialidad especialidad) {
		return especialidadDao.save(especialidad);
	}
	
	@Override
	public Especialidad guardarEspecialidad(Especialidad especialidad) {
		especialidad.setId(this.generarId());
		return especialidadDao.save(especialidad);
	}
	
	@Override
	public void eliminarEspecialidad(int id) {
		especialidadDao.deleteById(id);
	}
	
	@Override
	public Especialidad findById(int id) {
		return especialidadDao.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Especialidad> listarEspecialidad(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int generarId() {
		try {
			
			return especialidadDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		
		catch(NullPointerException e) {
			return 1;
		}
	}

	@Override
	public Especialidad verEspecialidad(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
