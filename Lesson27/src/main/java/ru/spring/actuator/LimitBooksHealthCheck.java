package ru.spring.actuator;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.spring.repository.BookRepository;

import static org.springframework.boot.actuate.health.Health.down;
import static org.springframework.boot.actuate.health.Health.up;

@Component
@AllArgsConstructor
public class LimitBooksHealthCheck implements HealthIndicator {

    private final BookRepository repository;

    @Override
    public Health health() {
        long count = repository.count();
        return count > 10
                ? up().withDetail("Too many books", count).build()
                : down().withDetail("Count of books is normal", count).build();
    }
}
