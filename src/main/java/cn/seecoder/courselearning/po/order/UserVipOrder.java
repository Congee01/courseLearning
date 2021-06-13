package cn.seecoder.courselearning.po.order;

import cn.seecoder.courselearning.vo.order.UserVipOrderVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

public class UserVipOrder {
    private Integer id;
    private Integer userId;
    private Integer cost;
    private Integer status;
    private String validTime;
    private LocalDateTime createTime;
    public Integer getOrderId(){
        return id;
    }
    public void setOrderId(Integer id){
        this.id=id;
    }
    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer UserId){
        this.userId=UserId;
    }
    public Integer getCost(){
        return cost;
    }
    public void setCost(Integer cost){
        this.cost=cost;
    }
    public LocalDateTime getCreateTime(){
        return createTime;
    }
    public String getValidTime(){return validTime;}
    public void setValidTime(String validTime){
        this.validTime=validTime;
    }
    public void setCreateTime(LocalDateTime createTime){
        this.createTime=createTime;
    }
    public Integer getStatus(){
        return status;
    }
    public void setStatus(Integer status){
        this.status=status;
    }
    public UserVipOrder(){
    }

    public UserVipOrder(@NonNull UserVipOrderVO userVipOrderVO){
        this.id=userVipOrderVO.getOrderId();
        this.userId=userVipOrderVO.getUserId();
        this.cost=userVipOrderVO.getCost();
        this.createTime=userVipOrderVO.getCreateTime();
        this.validTime=userVipOrderVO.getValidTime();
        this.status=userVipOrderVO.getStatus();
    }

}
