package cc.mcyou.glimmermeetingbackend.controller;

import cc.mcyou.glimmermeetingbackend.dao.RoomInfoRepository;
import cc.mcyou.glimmermeetingbackend.dao.UserInfoRepository;
import cc.mcyou.glimmermeetingbackend.data.LoginData;
import cc.mcyou.glimmermeetingbackend.data.Room_Info;
import cc.mcyou.glimmermeetingbackend.data.User_Info;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/rooms")
public class RoomInfoController {

    @Autowired
    RoomInfoRepository roomInfoRepository;

    @Resource
    LoginData loginData;

    @PutMapping
    public Object putRoom(@RequestParam("id")int id, @RequestParam("name")String name,  @RequestParam("info")String info,  @RequestParam("location")String location, @RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }
        Room_Info room = roomInfoRepository.findById(id);
        room.setName(name);
        room.setInfo(info);
        room.setLocation(location);
        roomInfoRepository.save(room);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "修改成功！");
        return response;
    }

    @DeleteMapping
    public Object deleteRoom(@RequestParam("id")int id,  @RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }

        roomInfoRepository.deleteById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "删除成功！");
        return response;
    }

    @GetMapping
    public Object getRooms(@RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！");
            return response;
        }
        return  roomInfoRepository.findAll();
    }

    @PostMapping
    public Object PostRoom(@RequestParam("name")String name,@RequestParam("info")String info, @RequestParam("location")String location, @RequestParam("token")String token){

        if(!loginData.getToken().containsKey(token) || loginData.getToken().get(token)!=1){
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "没有权限，请先登录管理员账号！"+loginData.getToken().get(token));
            return response;
        }
        Room_Info room = new Room_Info();
        room.setName(name);
        room.setInfo(info);
        room.setLocation(location);
        roomInfoRepository.save(room);
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "创建成功！");
        return response;
    }
}
