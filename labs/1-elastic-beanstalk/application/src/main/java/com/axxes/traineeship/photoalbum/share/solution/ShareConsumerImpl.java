package com.axxes.traineeship.photoalbum.share.solution;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.axxes.traineeship.photoalbum.share.ShareConsumer;
import com.axxes.traineeship.photoalbum.share.entity.ShareMessage;
import com.axxes.traineeship.photoalbum.share.entity.SqsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShareConsumerImpl implements ShareConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShareConsumerImpl.class);

    private final AmazonSQS sqs;
    private final String queueUrl;
    private final ObjectMapper objectMapper;

    @Autowired
    public ShareConsumerImpl(@Value("${images.share.queue}") String queueUrl, ObjectMapper objectMapper) {
        this(AmazonSQSClientBuilder.standard().withRegion(Regions.EU_WEST_1).build(), queueUrl, objectMapper);
    }

    public ShareConsumerImpl(AmazonSQS sqs, String queueUrl, ObjectMapper objectMapper) {
        this.sqs = sqs;
        this.queueUrl = queueUrl;
        this.objectMapper = objectMapper;
    }

    @Override
    @Scheduled(fixedRate = 2000L)
    public void handleMessages() {
        final ReceiveMessageResult receiveMessageResult = sqs.receiveMessage(queueUrl);
        receiveMessageResult.getMessages().stream()
                .map(msg -> {
                    final SqsMessage sqsMessage = parse(msg.getBody());
                    return handle(sqsMessage.getMessage(), msg.getReceiptHandle());
                })
                .forEach(id -> sqs.deleteMessage(queueUrl, id));
    }

    private String handle(ShareMessage message, String messageId) {
        LOGGER.info("Received {} with id [{}]", message, messageId);
        return messageId;
    }

    private SqsMessage parse(String body) {
        try {
            return objectMapper.readValue(body, SqsMessage.class);
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to parse SQS message.", e);
            return null; // Good luck lol, I don't know why it's broken.
        }
    }
}
