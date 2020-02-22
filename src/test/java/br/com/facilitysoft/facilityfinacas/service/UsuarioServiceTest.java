package br.com.facilitysoft.facilityfinacas.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.facilitysoft.facilityfinacas.exception.RegraNegocioException;
import br.com.facilitysoft.facilityfinacas.model.entity.Usuario;
import br.com.facilitysoft.facilityfinacas.model.repository.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;
	
	@Autowired
	UsuarioRepository repository;
	
	@Test()
	public void deveValidarEmail() {
		//Cenario
		repository.deleteAll();		
		//Ação
		service.validarEmail("adilson@email.com");
	}
	
	@Test
	public void deveLancarErroAoValidarEmailQuandoEmailExistir() {
		//Cenario
		Usuario usuario = Usuario.builder().nome("Adilson").email("adilson@email.com").build();
		repository.save(usuario);
		
		//Ação
		org.junit.jupiter.api.Assertions.assertThrows(RegraNegocioException.class, () -> service.validarEmail("adilson@email.com")) ;
	}
}
