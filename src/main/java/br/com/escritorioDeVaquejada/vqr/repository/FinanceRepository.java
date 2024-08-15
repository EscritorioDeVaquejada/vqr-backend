package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.FinanceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinanceRepository extends JpaRepository<FinanceModel, UUID> {
}
