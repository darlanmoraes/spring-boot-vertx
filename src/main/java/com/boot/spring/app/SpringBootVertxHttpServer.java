package com.boot.spring.app;

import com.boot.spring.person.PersonServiceImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;
import org.springframework.stereotype.Component;

/**
 * Created by darlan on 25/01/17.
 */
@Component
public class SpringBootVertxHttpServer extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        VertxResteasyDeployment deployment = new VertxResteasyDeployment();
        deployment.start();
        deployment.getRegistry().addPerInstanceResource(PersonServiceImpl.class);
        vertx.createHttpServer()
                .requestHandler(new VertxRequestHandler(vertx, deployment))
                .listen(8080, ar -> {
                    System.out.println("Server started on port "+ 8080);
                });

    }

}
