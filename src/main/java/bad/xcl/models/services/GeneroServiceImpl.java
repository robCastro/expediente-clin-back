package bad.xcl.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bad.xcl.models.dao.IGeneroDao;
import bad.xcl.models.entity.Especialidad;
import bad.xcl.models.entity.Genero;

@Service
public class GeneroServiceImpl implements IGeneroService {

	@Autowired
	private IGeneroDao generoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Genero> findAll() {
		return (List<Genero>) generoDao.findAll();
	}
	
	@Override
	public Genero save(Genero genero) {
		return generoDao.save(genero);
	}

	@Override
	public Genero guardarGenero(Genero genero) {
		genero.setId(this.generarId());
		return generoDao.save(genero);
	}

	@Override
	public List<Genero> listarGeneros() {
		return generoDao.findAllByActivo(true);
	}
	@Override
	public List<Genero> listarActivos() {
		return (List<Genero>) generoDao.findAllByActivo(true);
	}

	@Override
	public void eliminarGenero(int id) {
		generoDao.deleteById(id);
	}

	@Override
	public int generarId() {
		try {
			
			return generoDao.findFirstByOrderByIdDesc().getId() + 1;
		}
		
		catch(NullPointerException e) {
			return 1;
		}
	}

	@Override
	public Genero findById(int id) {
		return generoDao.findById(id).orElse(null);
	}



}

