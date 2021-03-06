package br.com.facilitysoft.facilityfinacas.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.facilitysoft.facilityfinacas.exception.RegraNegocioException;
import br.com.facilitysoft.facilityfinacas.model.entity.Lancamento;
import br.com.facilitysoft.facilityfinacas.model.enums.StatusLancamento;
import br.com.facilitysoft.facilityfinacas.model.repository.LancamentoRepository;
import br.com.facilitysoft.facilityfinacas.service.LancamentoService;
//essa anotaçõe define essa class como um bean gerenciavel
@Service
public class LancamentoServiceImp implements LancamentoService {
	
	private LancamentoRepository repository;
	
	public LancamentoServiceImp(LancamentoRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional 
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		return repository.save(lancamento);
	}

	@Override
	@Transactional 
	public Lancamento atualizar(Lancamento lancamento) {	
		Objects.requireNonNull(lancamento.getId());
		validar(lancamento);
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		Example example = Example.of(lancamentoFiltro, 
				                    ExampleMatcher.matching()
				                    .withIgnoreCase()
				                    .withStringMatcher(StringMatcher.CONTAINING)
				                    );
		return repository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
		
	}

	@Override
	public void validar(Lancamento lancamento) {
		if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma Descrição valida. ");
		}
		if (lancamento.getMes() ==  null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês valido. ");
		}
		if (lancamento.getAno() == null || lancamento.getAno().toString().length() !=4) {
			throw new RegraNegocioException("Informe um  Ano valido. ");
		}
		if (lancamento.getUsuario() == null || lancamento.getUsuario().getId() == null) {
			throw new RegraNegocioException("Informe um Usuário. ");
		}
		if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um Valor valido. ");
		}
		if (lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um Tipo de Lançamento. ");
		}
	}

}
