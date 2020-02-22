package br.com.facilitysoft.facilityfinacas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.facilitysoft.facilityfinacas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{


}
