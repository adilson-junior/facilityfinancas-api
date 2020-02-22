package br.com.facilitysoft.facilityfinacas.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.facilitysoft.facilityfinacas.exception.ErroAutenticacao;
import br.com.facilitysoft.facilityfinacas.exception.RegraNegocioException;
import br.com.facilitysoft.facilityfinacas.model.entity.Usuario;
import br.com.facilitysoft.facilityfinacas.model.repository.UsuarioRepository;
import br.com.facilitysoft.facilityfinacas.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository repository; 	
   
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado para o email informado.");
		}
		if(!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
			
		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvar(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuario cadastrado com este email. ");
		}		
	}

	@Override
	public Usuario edtar(Usuario usuairo) {
		// TODO Auto-generated method stub
		return null;
	}

}
