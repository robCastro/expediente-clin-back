package bad.xcl.models.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.ITratamientoDao;
import bad.xcl.models.entity.HistorialClinico;
import bad.xcl.models.entity.Tratamiento;

@Service
public class TratamientoServiceImpl implements ITratamientoService {
	
	@Autowired
	private ITratamientoDao tratamientoDao;
	
	@Override
	public Integer generarId() {
		try {
			return tratamientoDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		catch (NullPointerException e) {
			return 1;
		}
	}
	
	@Override
	public Tratamiento guardar(Tratamiento tratamiento) {
		tratamiento.setId(this.generarId());
		return tratamientoDao.save(tratamiento);
	}
	
	@Override
	public void delete(int id) {
		tratamientoDao.deleteById(id);
	}
	
	@Override
	public List<Tratamiento> listarPorConsulta(Integer id_consulta){
		return (List<Tratamiento>) tratamientoDao.listarPorConsulta(id_consulta);
	}
	
	

}
