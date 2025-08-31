package io.construct.agentconversation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan(basePackages = {"io.construct.corelib", "io.construct.agentconversation"})
@EntityScan(basePackages = {"io.construct.lib.entities"})
public class AgentConversationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentConversationApplication.class, args);
    }

}
