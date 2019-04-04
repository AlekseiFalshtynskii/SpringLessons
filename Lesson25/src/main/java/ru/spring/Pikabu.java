package ru.spring;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.spring.domain.RequestPublish;

@MessagingGateway
public interface Pikabu {

    @Gateway(requestChannel = "publishing.input")
    void publish(RequestPublish requestPublish);
}
