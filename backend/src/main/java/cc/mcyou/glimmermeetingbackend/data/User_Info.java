package cc.mcyou.glimmermeetingbackend.data;

import com.alibaba.fastjson.JSON;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name="user_info")
@Table(name="user_info")
@NoArgsConstructor
public class User_Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password; //bcrypt加密
    @NonNull
    private String real_name;
    @NonNull
    private String related_booking; //JSON格式，是List
    @NonNull
    private String related_meeting; //JSON格式，是List

    public List<Integer> getRelatedBooking(){
        return JSON.parseArray(this.related_booking, Integer.class);
    }

    public void setRelatedBooking(List<Integer> related_booking){
        this.related_booking = JSON.toJSONString(related_booking);
    }

    public List<Integer> getRelatedMeeting(){
        return JSON.parseArray(this.related_meeting, Integer.class);
    }

    public void setRelatedMeeting(List<Integer> related_meeting){
        this.related_meeting = JSON.toJSONString(related_meeting);
    }

    public void setPassword(String passwd){
        this.password = BCrypt.hashpw(passwd, BCrypt.gensalt() );
    }

    public boolean matchPassword(String passwd){
        return BCrypt.checkpw(passwd, this.password);
    }
}
