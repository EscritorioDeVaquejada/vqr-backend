package br.com.escritorioDeVaquejada.vqr.repositories;

import br.com.escritorioDeVaquejada.vqr.models.Finance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FinanceRepository extends JpaRepository<Finance, UUID> {
}
