package cn.seecoder.courselearning.serviceimpl.order;

import cn.seecoder.courselearning.mapperservice.order.UserVipOrderMapper;
import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.mapperservice.user.UserVipMapper;
import cn.seecoder.courselearning.po.order.UserVipOrder;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.po.user.UserVip;
import cn.seecoder.courselearning.service.order.UserVipOrderService;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.order.UserVipOrderVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserVipOrderServiceImpl implements UserVipOrderService {
    @Resource
    UserVipOrderMapper userVipOrderMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;
    @Resource
    UserVipMapper userVipMapper;
    @Override
    public ResultVO<UserVipOrderVO> createVipOrder(UserVipOrderVO userVipOrderVO) {
        UserVipOrder userVipOrder=new UserVipOrder(userVipOrderVO);
        LocalDateTime localDateTime=LocalDateTime.now();
        userVipOrder.setCreateTime(localDateTime);
        if(userVipOrderMapper.insert(userVipOrder) > 0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "订单创建成功！",new UserVipOrderVO(userVipOrder));
        }
        else{
            return new ResultVO<>(Constant.REQUEST_FAIL,"服务器错误");
        }
    }

    @Override
    public ResultVO<UserVipOrderVO> updateVipOrder(Integer vipOrderId) {
        return null;
    }


    @Override
    public ResultVO<Boolean> payVipOrder(Integer vipOrderId) {
        UserVipOrder userVipOrder=userVipOrderMapper.selectByPrimaryKey(vipOrderId);
        UserVO userVO= userService.getUser(userVipOrder.getUserId());
        UserVip userVip=userVipMapper.selectByPrimaryKey(userVO.getId());
        if(userVip!=null){
            userVipMapper.updateValid(userVO.getId(),0);
        }
        //当前用户可以支付该会员订单时
        if(userVO.getBalance() >= userVipOrder.getCost()){
            userService.decreaseBalance(userVipOrder.getUserId(),userVipOrder.getCost());
            userVipOrder.setStatus(Constant.ORDER_STATUS_SUCCESS);
            LocalDateTime localDateTime=LocalDateTime.now();
            UserVip userVip1=new UserVip();
            userVip1.setUserId(userVO.getId());
            userVip1.setStartTime(localDateTime);
            userVip1.setEndTime(handleEndTime(localDateTime,userVipOrder.getValidTime()));
            userVip1.setValid(1);
            userVipMapper.insert(userVip1);
            userVipOrderMapper.updateByPrimaryKey(userVipOrder);
            userMapper.updateIsVip(userVO.getId(),true);
            return new ResultVO<>(Constant.REQUEST_SUCCESS,"付款成功!");
        }
        else{
            return new ResultVO<>(Constant.REQUEST_FAIL,"付款失败，余额不足!");
        }
    }

    public LocalDateTime handleEndTime(LocalDateTime dateTime,String valid){
        if(valid.equals("1分钟")){
            return dateTime.plusMinutes(1L);
        }
        else if(valid.equals("1小时")){
            return dateTime.plusHours(1L);
        }
        else if(valid.equals("1天")){
            return dateTime.plusDays(1L);
        }
        else{
            return dateTime.plusDays(7L);
        }
    }
}
