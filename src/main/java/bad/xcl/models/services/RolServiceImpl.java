package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IRolDao;
import bad.xcl.models.entity.Rol;

@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDao rolDao;
	
	@Override
	public Rol save(Rol rol) {
		return rolDao.save(rol);
	}

	@Override
	public Rol guardar(Rol rol) {
		rol.setId(this.generarId());
		return rolDao.save(rol);
	}

	@Override
	public List<Rol> listar() {
		return (List<Rol>) rolDao.findAll();
	}

	@Override
	public void eliminar(Integer id) {
		rolDao.deleteById(id);
	}

	@Override
	public Integer generarId() {
		try {
			return rolDao.findFirstByOrderByIdDesc().getId() + 1;
		} 
		catch(NullPointerException e) {
			return 1;
		}
	}

	@Override
	public Rol findById(Integer id) {
		return rolDao.findById(id).orElse(null);
	}

}
