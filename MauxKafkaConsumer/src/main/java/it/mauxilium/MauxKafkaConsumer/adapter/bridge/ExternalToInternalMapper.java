package it.mauxilium.MauxKafkaConsumer.adapter.bridge;

import it.mauxilium.MauxKafkaConsumer.business.model.MessageModel;
import it.mauxilium.MauxKafkaConsumer.adapter.model.MessageOnNetworkModel;

import java.util.function.Function;

public class ExternalToInternalMapper {
    public static Function<MessageOnNetworkModel, MessageModel> remap = kafkaMsg -> new MessageModel(
            kafkaMsg.getItemIndex(),
            kafkaMsg.getHowToSend(),
            kafkaMsg.getTopic(),
            kafkaMsg.getSessionId());
}
