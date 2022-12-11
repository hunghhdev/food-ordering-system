package dev.hunghh.ordering.system.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
