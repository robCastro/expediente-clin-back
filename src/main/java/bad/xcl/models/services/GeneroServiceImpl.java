package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bad.xcl.models.dao.IGeneroDao;
import bad.xcl.models.entity.Genero;

@Service
public class GeneroServiceImpl implements IGeneroService{
	
	@Autowired
	private IGeneroDao generoDao;
	
	@Override
	public Genero save(Genero genero) {
		return generoDao.save(genero);
	}

	@Override
	public Genero guardar(Genero genero) {
		genero.setId(this.generarId());
		return generoDao.save(genero);
	}

	@Override
	public List<Genero> listar() {
		return (List<Genero>) generoDao.findAll();
	}
	
	@Override
	public List<Genero> listarActivos() {
		return (List<Genero>) generoDao.findAllByActivo(true);
	}

	@Override
	public void eliminar(Integer id) {
		Genero genero = generoDao.findById(id).orElse(null);
		if(genero != null) {
			genero.setActivo(false);
			generoDao.save(genero);
		}
	}

	@Override
	public Integer generarId() {
		try {
			return generoDao.findFirstByOrderByIdDesc().getId() + 1;
		} 
		catch(NullPointerException e) {
			return 1;
		}
	}

	@Override
	public Genero findById(Integer id) {
		return generoDao.findById(id).orElse(null);
	}

}
