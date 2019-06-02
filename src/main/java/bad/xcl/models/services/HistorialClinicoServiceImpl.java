package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IHistorialClinicoDao;
import bad.xcl.models.entity.HistorialClinico;

@Service
public class HistorialClinicoServiceImpl implements IHistorialClinicoService {
	
	@Autowired
	private IHistorialClinicoDao historialDao;
	
	@Override
	public Integer generarId() {
		try {
			return historialDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		catch (NullPointerException e) {
			return 1;
		}
	}
	
	@Override
	public HistorialClinico guardar(HistorialClinico historial) {
		historial.setId(this.generarId());
		return historialDao.save(historial);
	}
	
	@Override
	public void eliminar(Integer id) {
		HistorialClinico historial = historialDao.findById(id).orElse(null);
		if(historial != null) {
			historial.setActivo(false);
			historialDao.save(historial);
		}
	}
	
	@Override
	public List<HistorialClinico> listarPorPaciente(Integer id_paciente){
		return (List<HistorialClinico>) historialDao.listarPorPaciente(id_paciente);
	}

}
