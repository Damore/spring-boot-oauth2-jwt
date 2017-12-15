package br.com.oauth.config;


import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {
    
    public CustomTokenEnhancer() {
        Resource resource = new ClassPathResource("mytest.jks");
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(resource, "mypass".toCharArray());
        super.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));
    }
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	DefaultOAuth2AccessToken defaultToken = new DefaultOAuth2AccessToken(accessToken.getValue());
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization", authentication.getName() + randomAlphabetic(4));
        defaultToken.setAdditionalInformation(additionalInfo);
        defaultToken.setRefreshToken(accessToken.getRefreshToken());
        OAuth2AccessToken enhance = super.enhance(defaultToken, authentication);
        return enhance;
    }

}