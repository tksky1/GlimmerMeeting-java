package cc.mcyou.glimmermeetingbackend.dao;

import cc.mcyou.glimmermeetingbackend.data.Room_Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RoomInfoRepository extends JpaRepository<Room_Info, Integer> {

    public ArrayList <Room_Info> findAll();
    public  Room_Info findById(int id);
}
