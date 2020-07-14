package com.dsmbamboo.api.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FcmConfig {

    @Bean
    public FirebaseApp getFirebaseApp() throws IOException {
        InputStream resourceStream = new ClassPathResource("firebase-adminsdk-credential.json").getInputStream();
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(resourceStream))
                .build();
        return FirebaseApp.initializeApp(options);
    }

}
