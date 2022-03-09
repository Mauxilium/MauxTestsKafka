package it.mauxilium.MauxKafkaProducer.framework.mapper;

import it.mauxilium.MauxKafkaProducer.business.model.MessageModel;
import it.mauxilium.MauxKafkaProducer.framework.model.MessageOnNetwork;

import java.util.function.Function;

public class MessageMapper {

    public static Function<MessageModel, MessageOnNetwork> internalToExternalMapper = msg -> new MessageOnNetwork(
            msg.getItemIndex(),
            msg.getHowToSend(),
            msg.getSessionId(),
            msg.getTimestamp(),
            msg.getReceiverSleepMSec()
    );
}
