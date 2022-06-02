package dev.hunghh.ordering.system.payment.service.domain;

import dev.hunghh.ordering.system.payment.service.domain.entity.CreditEntry;
import dev.hunghh.ordering.system.payment.service.domain.entity.CreditHistory;
import dev.hunghh.ordering.system.payment.service.domain.entity.Payment;
import dev.hunghh.ordering.system.payment.service.domain.event.PaymentEvent;

import java.util.List;

public interface PaymentDomainService {

    PaymentEvent validateAndInitiatePayment(Payment payment,
                                            CreditEntry creditEntry,
                                            List<CreditHistory> creditHistories,
                                            List<String> failureMessages);

    PaymentEvent validateAndeCancelPayment(Payment payment,
                                           CreditEntry creditEntry,
                                           List<CreditHistory> creditHistories,
                                           List<String> failureMessages);
}
