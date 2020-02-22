package br.com.facilitysoft.facilityfinacas.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.facilitysoft.facilityfinacas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@SpringBootTest
public class UsuarioRepositoryTeste {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void verificarEmeilExistente() {
		//Cenario
		Usuario usuario = criarUsuario();
        entityManager.persist(usuario);		
		//Ação
		boolean result = repository.existsByEmail("adilson@email.com");		
		//Vericação
		Assertions.assertThat(result).isTrue();		
	}
	
	@Test
	public void retornarFalsoQuandoNãoHouverUsuarioCadastradoComEmail() {
		//Cenario
		
		//Ação
		boolean result = repository.existsByEmail("adilson@email.com");		
		//Verificação
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void devePersistirUsuarioNaBase() {
		//Cenario
		Usuario usuario = criarUsuario();
		//Ação
		Usuario usuarioSalva = repository.save(usuario);
		
		//Teste
		Assertions.assertThat(usuarioSalva.getId()).isNotNull();
	}
	
	@Test 
	public void deveBuscarUsuarioPorEmail() {
		//Cenario
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		//Verificação
		Optional<Usuario> result = repository.findByEmail("julya@email.com");
		
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	public static Usuario criarUsuario() {
		return Usuario.builder()
                .nome("Júlya")
                .email("julya@email.com")
                .senha("123456")
                .build();
	}
	
}
