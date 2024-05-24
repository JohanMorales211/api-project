package com.parcial.airline_service.MessagingConfig;
import com.parcial.airline_service.servicies.DestinyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor

public class Mensajes {

    private final DestinyService destinyService;


    @RabbitListener(queues = Constantes.QUEUE )
    public Object receiveMessageAndReply(String nombre) {
        boolean buscado = destinyService.existsByName(nombre);
        System.out.println("Buscado: (destino-service) " + buscado);
        return buscado;
    }

}
