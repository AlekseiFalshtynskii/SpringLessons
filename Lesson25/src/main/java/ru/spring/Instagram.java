package ru.spring;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.spring.domain.RequestPublish;
import ru.spring.domain.Topic;

import java.util.Collection;

@MessagingGateway
public interface Instagram {

    @Gateway(requestChannel = "moderationChannel", replyChannel = "topicChannel")
    Collection<Topic> process(Collection<RequestPublish> orderItem);
}
