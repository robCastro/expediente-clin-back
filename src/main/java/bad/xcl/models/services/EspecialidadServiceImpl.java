package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IEspecialidadDao;
import bad.xcl.models.entity.Especialidad;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired
	private IEspecialidadDao especialidadDao;
	
	@Override
	public Especialidad save(Especialidad especialidad) {
		return especialidadDao.save(especialidad);
	}

	@Override
	public Especialidad guardar(Especialidad especialidad) {
		especialidad.setId(this.generarId());
		return especialidadDao.save(especialidad);
	}

	@Override
	public List<Especialidad> listar() {
		return (List<Especialidad>) especialidadDao.findAll();
	}
	
	@Override
	public List<Especialidad> listarActivos() {
		return (List<Especialidad>) especialidadDao.findAllByActivo(true);
	}

	@Override
	public void eliminar(Integer id) {
		Especialidad especialidad = especialidadDao.findById(id).orElse(null);
		if(especialidad != null) {
			especialidad.setActivo(false);
			especialidadDao.save(especialidad);
		}
	}

	@Override
	public Integer generarId() {
		try {
			return especialidadDao.findFirstByOrderByIdDesc().getId() + 1;
		} 
		catch(NullPointerException e) {
			return 1;
		}
	}

	@Override
	public Especialidad findById(Integer id) {
		return especialidadDao.findById(id).orElse(null);
	}
}
