package cc.mcyou.glimmermeetingbackend.config;

import cc.mcyou.glimmermeetingbackend.data.LoginData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {
    @Bean
    public LoginData configLoginData(){
        return new LoginData();
    }
}
