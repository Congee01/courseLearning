package cn.seecoder.courselearning.service.order;


import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.order.UserVipOrderVO;

public interface UserVipOrderService {
    ResultVO<UserVipOrderVO> createVipOrder(UserVipOrderVO userVipOrderVO);

    ResultVO<UserVipOrderVO> updateVipOrder(Integer vipOrderId);

    ResultVO<Boolean> payVipOrder(Integer vipOrderId);
}
