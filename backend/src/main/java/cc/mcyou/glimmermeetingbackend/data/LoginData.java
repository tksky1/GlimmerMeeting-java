package cc.mcyou.glimmermeetingbackend.data;

import java.util.HashMap;
import java.util.Map;

public class LoginData {
    Map<String, Integer> token;

    public LoginData(){
        token = new HashMap<>();
    }

    public Map<String, Integer> getToken(){
        return token;
    }
}
