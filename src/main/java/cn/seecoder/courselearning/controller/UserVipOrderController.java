package cn.seecoder.courselearning.controller;

import cn.seecoder.courselearning.service.order.UserVipOrderService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.order.UserVipOrderVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user_vip_order")
public class UserVipOrderController {
    @Resource
    private UserVipOrderService userVipOrderService;


    @PostMapping("/create")
    public ResultVO<UserVipOrderVO> createVipOrder(@RequestBody UserVipOrderVO userVipOrderVO){
        return userVipOrderService.createVipOrder(userVipOrderVO);
    }
    @PostMapping("/update")
    public ResultVO<UserVipOrderVO> updateVipOrder(@RequestParam Integer vipOrderId){
        return userVipOrderService.updateVipOrder(vipOrderId);
    }
    @PostMapping("/{vipOrderId}")
    public ResultVO<Boolean> payVipOrder(@PathVariable Integer vipOrderId){
        return userVipOrderService.payVipOrder(vipOrderId);
    }
}
