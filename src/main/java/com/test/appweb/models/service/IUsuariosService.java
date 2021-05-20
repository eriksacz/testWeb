package com.test.appweb.models.service;

import java.util.List;

import com.test.appweb.models.entity.Usuario;


public interface IUsuariosService {
	
	void guardar(Usuario usuario);
	
	void eliminar(Integer idUsuario);
	
	List<Usuario> buscarTodos();
	
	List<Usuario> buscarRegistrados();
	
	Usuario buscarPorId(Integer idUsuario);
	
	Usuario buscarPorUsername(String username);
	
	int bloquear(int idUsuario);
	
	int activar(int idUsuario);
}
