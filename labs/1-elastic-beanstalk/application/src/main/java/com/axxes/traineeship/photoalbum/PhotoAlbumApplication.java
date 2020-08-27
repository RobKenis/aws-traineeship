package com.axxes.traineeship.photoalbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PhotoAlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoAlbumApplication.class, args);
	}

}
