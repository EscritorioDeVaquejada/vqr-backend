package br.com.escritorioDeVaquejada.vqr.service.implementation;

import br.com.escritorioDeVaquejada.vqr.mapper.Mapper;
import br.com.escritorioDeVaquejada.vqr.model.PaymentModel;
import br.com.escritorioDeVaquejada.vqr.repository.PaymentRepository;
import br.com.escritorioDeVaquejada.vqr.service.FinanceService;
import br.com.escritorioDeVaquejada.vqr.service.PaymentService;
import br.com.escritorioDeVaquejada.vqr.vo.payment.PaymentSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImplementation implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final Mapper mapper;

    @Autowired
    public PaymentServiceImplementation(
            PaymentRepository paymentRepository,
            Mapper mapper) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
    }

    @Override
    public PaymentModel registerNewPayment(PaymentSaveVO paymentInfo) {
        PaymentModel paymentToBeSaved = mapper.parseObject(paymentInfo, PaymentModel.class);
        paymentToBeSaved.setDateTime(LocalDateTime.now());
        return paymentRepository.save(paymentToBeSaved);
    }
}
