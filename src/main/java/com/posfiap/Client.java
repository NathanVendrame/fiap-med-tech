package com.posfiap;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.posfiap.agendamento.UsuarioServiceGrpc;
import com.posfiap.agendamento.UsuarioRequest;
import com.posfiap.agendamento.Usuario;

public class Client {
    public static void main(String[] args) {
        // Configurando o canal
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        // Criando o stub
        UsuarioServiceGrpc.UsuarioServiceBlockingStub stub = UsuarioServiceGrpc.newBlockingStub(channel);

        // Fazendo a requisição
        UsuarioRequest request = UsuarioRequest.newBuilder()
                .setId(1)
                .build();

        // Recebendo a resposta
        Usuario response = stub.consultarUsuario(request);

        // Imprimindo os detalhes do veículo
        System.out.println("Informações do veículo:");
        System.out.println("Nome: " + response.getNome());
        System.out.println("Email: " + response.getEmail());

        // Encerrando o canal
        channel.shutdown();
    }
}
