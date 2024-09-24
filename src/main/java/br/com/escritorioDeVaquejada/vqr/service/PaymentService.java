package br.com.escritorioDeVaquejada.vqr.service;

import br.com.escritorioDeVaquejada.vqr.model.PaymentModel;
import br.com.escritorioDeVaquejada.vqr.repository.PaymentRepository;
import br.com.escritorioDeVaquejada.vqr.vo.payment.PaymentSaveVO;

public interface PaymentService {
    PaymentModel registerNewPayment(PaymentSaveVO payment);
}
