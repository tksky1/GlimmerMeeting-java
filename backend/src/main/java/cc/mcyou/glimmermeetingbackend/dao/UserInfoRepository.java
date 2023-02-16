
package cc.mcyou.glimmermeetingbackend.dao;

import cc.mcyou.glimmermeetingbackend.data.User_Info;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserInfoRepository extends JpaRepository<User_Info, Integer> {

    public ArrayList <User_Info> findAll();
    public User_Info findById(int id);

    User_Info findByUsername(String username);
}
