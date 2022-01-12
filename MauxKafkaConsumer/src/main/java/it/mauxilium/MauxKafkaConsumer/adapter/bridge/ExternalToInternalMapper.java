package it.mauxilium.MauxKafkaConsumer.adapter.bridge;

import it.mauxilium.MauxKafkaConsumer.framework.model.MessageOnNetwork;
import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;

import java.util.function.Function;

public class ExternalToInternalMapper {
    public static Function<MessageOnNetwork, MessageModel> remap = kafkaMsg -> new MessageModel(
            kafkaMsg.getItemIndex(),
            kafkaMsg.getHowToSend(),
            kafkaMsg.getTopic(),
            kafkaMsg.getSessionId(),
            kafkaMsg.getTimeStamp());
}
