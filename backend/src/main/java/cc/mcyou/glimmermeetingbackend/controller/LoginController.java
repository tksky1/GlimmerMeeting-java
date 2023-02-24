package cc.mcyou.glimmermeetingbackend.controller;

import cc.mcyou.glimmermeetingbackend.dao.UserInfoRepository;
import cc.mcyou.glimmermeetingbackend.data.LoginData;
import cc.mcyou.glimmermeetingbackend.data.User_Info;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Resource
    LoginData loginData;

    @PostMapping
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password){
        User_Info user_info = userInfoRepository.findByUsername(username);
        HashMap<String, String> response = new HashMap<>();
        if (user_info == null || !user_info.matchPassword(password)){
            response.put("message", "用户名或密码错误！");
            System.out.println("用户"+username+"登录失败，user_info："+user_info);
            return response;
        }
        String uuid = UUID.randomUUID().toString();
        loginData.getToken().put(uuid, user_info.getId());
        return uuid;
    }

}
