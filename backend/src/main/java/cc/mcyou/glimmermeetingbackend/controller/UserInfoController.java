package cc.mcyou.glimmermeetingbackend.controller;

import cc.mcyou.glimmermeetingbackend.dao.UserInfoRepository;
import cc.mcyou.glimmermeetingbackend.data.LoginData;
import cc.mcyou.glimmermeetingbackend.data.User_Info;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/people")
@CrossOrigin(origins = "*")
public class UserInfoController {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Resource
    LoginData loginData;

    @PutMapping
    public Object putUser(@RequestParam("id")int id, @RequestParam("username")String username,  @RequestParam("real_name")String real_name, @RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }
        User_Info user = userInfoRepository.findById(id);
        user.setUsername(username);
        user.setReal_name(real_name);
        userInfoRepository.save(user);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "修改成功！");
        return response;
    }

    @DeleteMapping
    public Object deleteUser(@RequestParam("id")int id,  @RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }

        userInfoRepository.deleteById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "删除成功！");
        return response;
    }

    @GetMapping
    public Object GetUsers(@RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }

        return userInfoRepository.findAll();
    }

    @PostMapping
    public Object PostUser(@RequestParam("username")String username,@RequestParam("password")String password, @RequestParam("real_name")String real_name, @RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }

        System.out.println("收到post用户请求");
        User_Info user = new User_Info();
        user.setUsername(username);
        user.setPassword(password);
        user.setReal_name(real_name);
        userInfoRepository.save(user);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "创建成功！");
        return response;
    }
}
