package com.axxes.traineeship.photoalbum.share;

import com.axxes.traineeship.photoalbum.image.entity.AlbumImage;
import org.springframework.stereotype.Component;

@Component
public class ShareListenerImpl implements ShareListener {

    @Override
    public void imageAdded(AlbumImage image) {
        //TODO: Lab 5 - Messaging - SNS
    }
}
