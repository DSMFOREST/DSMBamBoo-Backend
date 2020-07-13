package com.dsmbamboo.api.configs;

import com.google.firebase.FirebaseApp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FcmConfig {

    @Bean
    public FirebaseApp getFirebaseApp() {
        return FirebaseApp.initializeApp();
    }

}
