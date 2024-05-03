package com.me2.config;

import com.cloudinary.Cloudinary;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudConfiguration {

//    @Value("${cloud.name}")
//    private final String CLOUD_NAME;
//
//    @Value("${cloud.key}")
//    private final String CLOUD_API_KEY;
//
//    @Value("${cloud.secret}")
//    private final String CLOUD_API_SECRET;
//
//    public CloudConfiguration(String cloudName, String cloudApiKey, String cloudApiSecret) {
//        CLOUD_NAME = cloudName;
//        CLOUD_API_KEY = cloudApiKey;
//        CLOUD_API_SECRET = cloudApiSecret;
//    }

    private String CLOUD_NAME;
    private String CLOUD_API_KEY;
    private String CLOUD_API_SECRET;

    public CloudConfiguration() {
    }

    @Value("${cloud.name}")
    public void setCloudName(String cloudName) {
        this.CLOUD_NAME = cloudName;
    }

    @Value("${cloud.key}")
    public void setCloudApiKey(String cloudApiKey) {
        this.CLOUD_API_KEY = cloudApiKey;
    }

    @Value("${cloud.secret}")
    public void setCloudApiSecret(String cloudApiSecret) {
        this.CLOUD_API_SECRET = cloudApiSecret;
    }

    @Bean
    public Cloudinary getCloudinary(){
        Map<String, Object> config = new HashMap();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", CLOUD_API_KEY);
        config.put("api_secret", CLOUD_API_SECRET);
        config.put("secure", true);
        return new Cloudinary(config);
    }
}