package br.com.facilitysoft.facilityfinacas.service;

import br.com.facilitysoft.facilityfinacas.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String email, String senha);
	
	Usuario salvar(Usuario usuario);
	
	void validarEmail(String email);	
	
	Usuario edtar(Usuario usuairo);

}
