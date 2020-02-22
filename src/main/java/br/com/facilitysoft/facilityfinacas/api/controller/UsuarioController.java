package br.com.facilitysoft.facilityfinacas.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.facilitysoft.facilityfinacas.api.dto.UsuarioDTO;
import br.com.facilitysoft.facilityfinacas.exception.ErroAutenticacao;
import br.com.facilitysoft.facilityfinacas.exception.RegraNegocioException;
import br.com.facilitysoft.facilityfinacas.model.entity.Usuario;
import br.com.facilitysoft.facilityfinacas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
	private UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@PostMapping
	public ResponseEntity<Object> salvar (@RequestBody UsuarioDTO dto ) {
		Usuario usuario = Usuario.builder()
				                 .nome(dto.getNome())
				                 .email(dto.getEmail())
				                 .senha(dto.getSenha())
				                 .build();
		try {
			Usuario usuarioSalvo = usuarioService.salvar(usuario);
			return new ResponseEntity<Object>(usuarioSalvo, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity<Object> autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
