package com.test.appweb.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.appweb.models.dao.UsuariosRepository;
import com.test.appweb.models.entity.Usuario;

@Service
public class UsuariosServiceJpa implements IUsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepo;
	
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}


	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		Optional<Usuario> optional = usuariosRepo.findById(idUsuario);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return usuariosRepo.findByUsername(username);
	}

	@Override
	public List<Usuario> buscarRegistrados() {		
		return usuariosRepo.findByFechaNotNull();
	}

	@Transactional
	@Override
	public int bloquear(int idUsuario) {
		int rows = usuariosRepo.lock(idUsuario);
		return rows;
	}

	@Transactional
	@Override
	public int activar(int idUsuario) {
		int rows = usuariosRepo.unlock(idUsuario);
		return rows;
	}

}
