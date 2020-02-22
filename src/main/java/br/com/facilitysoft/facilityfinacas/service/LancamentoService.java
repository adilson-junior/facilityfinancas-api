package br.com.facilitysoft.facilityfinacas.service;


import java.util.List;

import br.com.facilitysoft.facilityfinacas.model.entity.Lancamento;
import br.com.facilitysoft.facilityfinacas.model.enums.StatusLancamento;

public interface LancamentoService {
	
	Lancamento salvar(Lancamento lancamento);
	
	Lancamento atualizar(Lancamento lancamento);
	
	void deletar(Lancamento lancamento);
	
	List<Lancamento> buscar(Lancamento lancamentoFiltro);
	
	void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	void validar(Lancamento lancamento);

}
