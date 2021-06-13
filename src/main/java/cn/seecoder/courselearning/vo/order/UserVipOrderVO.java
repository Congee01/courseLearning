package cn.seecoder.courselearning.vo.order;

import cn.seecoder.courselearning.po.order.UserVipOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserVipOrderVO {
    private Integer orderId;
    private Integer UserId;
    private Integer status;
    private LocalDateTime createTime;
    private String validTime;
    private Integer cost;
    public UserVipOrderVO(){
    }

    public UserVipOrderVO(@NonNull UserVipOrder userVipOrder){
        orderId=userVipOrder.getOrderId();
        UserId=userVipOrder.getUserId();
        status=userVipOrder.getStatus();
        createTime=userVipOrder.getCreateTime();
        cost=userVipOrder.getCost();
        validTime=userVipOrder.getValidTime();
    }
}
