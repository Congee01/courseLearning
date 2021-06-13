package cn.seecoder.courselearning.po.user;

import cn.seecoder.courselearning.vo.user.UserVipVO;
import lombok.NonNull;

import java.time.LocalDateTime;

public class UserVip {
    private  Integer id;

    private Integer userId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Integer valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getValid(){return valid;}

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public UserVip(){
    }

    public UserVip(@NonNull UserVipVO userVipVO){
        id=userVipVO.getId();
        userId=userVipVO.getUserId();
        startTime=userVipVO.getStartTime();
        endTime=userVipVO.getEndTime();
        valid=userVipVO.getValid();
    }
}
