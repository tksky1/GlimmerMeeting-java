package cc.mcyou.glimmermeetingbackend.data;

import com.alibaba.fastjson.JSON;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name="room_info")
@Table(name="room_info")
@NoArgsConstructor
public class Room_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String info;
    @NonNull
    private String location;
    @NonNull
    private String related_booking; //JSON格式，是List
    @NonNull
    private int stat_id;

    public List<Integer> getRelatedBooking(){
        return JSON.parseArray(this.related_booking, Integer.class);
    }

    public void setRelatedBooking(List<Integer> related_booking){
        this.related_booking = JSON.toJSONString(related_booking);
    }
}
