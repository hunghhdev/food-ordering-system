package dev.hunghh.ordering.system.saga;

public interface SagaStep<T> {
    void process(T data);
    void rollback(T data);
}
