package br.com.escritorioDeVaquejada.vqr.repository;

import br.com.escritorioDeVaquejada.vqr.model.PaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentModel, UUID> { }
