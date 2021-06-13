package cn.seecoder.courselearning.vo.user;

import cn.seecoder.courselearning.po.user.UserVip;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class UserVipVO {
    private Integer id;

    private Integer userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer valid;

    public UserVipVO(){}

    public UserVipVO(@NonNull UserVip userVip){
        id=userVip.getId();
        userId=userVip.getUserId();
        startTime=userVip.getStartTime();
        endTime=userVip.getEndTime();
        valid=userVip.getValid();
    }
}
