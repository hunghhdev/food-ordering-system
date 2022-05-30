package dev.hunghh.ordering.system.order.service.application.exception.handler;

import dev.hunghh.ordering.system.application.handler.ErrorDTO;
import dev.hunghh.ordering.system.application.handler.GlobalHandlerException;
import dev.hunghh.ordering.system.order.service.domain.exception.OrderDomainException;
import dev.hunghh.ordering.system.order.service.domain.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class OrderGlobalHandlerException extends GlobalHandlerException {

    @ResponseBody
    @ExceptionHandler(value = {OrderDomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handlerException(OrderDomainException orderDomainException) {
        log.error(orderDomainException.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(orderDomainException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {OrderNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handlerException(OrderNotFoundException orderNotFoundException) {
        log.error(orderNotFoundException.getMessage());
        return ErrorDTO.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(orderNotFoundException.getMessage())
                .build();
    }
}
