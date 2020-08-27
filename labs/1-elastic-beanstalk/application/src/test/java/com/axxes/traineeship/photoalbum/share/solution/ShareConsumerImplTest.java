package com.axxes.traineeship.photoalbum.share.solution;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.axxes.traineeship.photoalbum.share.ShareConsumer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ShareConsumerImplTest {

    private final AmazonSQS sqs = mock(AmazonSQS.class);

    private ShareConsumer consumer;

    @BeforeEach
    void setUp() {
        consumer = new ShareConsumerImpl(sqs, "https://robkenis.com/", new ObjectMapper());
        when(sqs.receiveMessage("https://robkenis.com/"))
                .thenReturn(new ReceiveMessageResult().withMessages(
                        new Message().withBody("{\n" +
                                "  \"Type\": \"Notification\",\n" +
                                "  \"MessageId\": \"480d00c9-a7c3-5fc1-a147-5708c8ed93aa\",\n" +
                                "  \"TopicArn\": \"arn:aws:sns:eu-west-1:040506089782:robs-backend-application-ShareTopic-VDP7GFEYXKE7\",\n" +
                                "  \"Message\": \"{\\\"albumId\\\":\\\"6cd2cc51-4266-4e10-8bc8-6f3ad7f4e002\\\",\\\"uploadedAt\\\":\\\"2020-08-27T08:21:02.280+00:00\\\",\\\"imageUrl\\\":\\\"https://robs-backend-application-imagesbucket-1mgfyi7xsx96s.s3.eu-west-1.amazonaws.com/avatar.png\\\"}\",\n" +
                                "  \"Timestamp\": \"2020-08-27T08:21:02.392Z\",\n" +
                                "  \"SignatureVersion\": \"1\",\n" +
                                "  \"Signature\": \"WOdggzFJjwXluFfeiRS6wz0bGQ7gAkpa1h8FjY/ny2S5JOllizPq+L74JKEIY+T2S/H4lidZ3SKqThJ4K8wTku88D8+BF7pDk9AGWH1SU7W/dnDWoLw5HS4GYAd69fcyzY6ak9wNt8gJK+9Zh4uQfdi6ujGK47jS2B3XNizqEnrQWnOR4wBeemxMS4ATAHP0dWyAPTQX5GLPejNBJZpZsQoSRcFOnc+bzROU/bLzkjH/l2YdDHVScU7IpcHk9ixnlc94y/gW6sOO89EQOARGziWEWnSspfW7rOE1b4Ske6/cfyFIE7NDweVDTzcLonMtRazmevYu/h0Un6HT/aLxow==\",\n" +
                                "  \"SigningCertURL\": \"https://sns.eu-west-1.amazonaws.com/SimpleNotificationService-a86cb10b4e1f29c941702d737128f7b6.pem\",\n" +
                                "  \"UnsubscribeURL\": \"https://sns.eu-west-1.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:eu-west-1:040506089782:robs-backend-application-ShareTopic-VDP7GFEYXKE7:2c8c581a-6c39-48ac-affd-be26d3c3e3a7\"\n" +
                                "}")
                ));
    }

    @Test
    void parse() {
        consumer.handleMessages();
        verify(sqs).deleteMessage("https://robkenis.com/", null);
    }
}
