package com.axxes.traineeship.photoalbum.share.solution;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import com.axxes.traineeship.photoalbum.share.ShareListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShareListenerImpl implements ShareListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShareListenerImpl.class);

    private final AmazonSNS sns;
    private final String shareTopic;
    private final ObjectMapper objectMapper;

    public ShareListenerImpl(@Value("${images.share.topic}") String shareTopic, ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
        sns = AmazonSNSClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
        this.shareTopic = shareTopic;
    }

    @Override
    public void imageAdded(AlbumImage image) {
        try {
            sns.publish(shareTopic, objectMapper.writeValueAsString(image));
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to publish share to SNS.", e);
        }
    }
}
