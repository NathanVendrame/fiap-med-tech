package com.posfiap;

import com.posfiap.server.GrpcServer;
import com.posfiap.service.UsuarioService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args){
        GrpcServer.create(6565, builder -> builder.addService(new UsuarioService()))
                .start()
                .await();
    }
}
