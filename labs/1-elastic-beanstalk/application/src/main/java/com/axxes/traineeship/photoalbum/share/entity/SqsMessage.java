package com.axxes.traineeship.photoalbum.share.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SqsMessage {

    @JsonProperty("TopicArn")
    private String topicArn;
    private ShareMessage message;
    @JsonIgnore
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getTopicArn() {
        return topicArn;
    }

    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }

    public ShareMessage getMessage() {
        return message;
    }

    public void setMessage(ShareMessage message) {
        this.message = message;
    }

    @JsonProperty("Message")
    private void unpackNested(String message) {
        try {
            this.message = objectMapper.readValue(message, ShareMessage.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
