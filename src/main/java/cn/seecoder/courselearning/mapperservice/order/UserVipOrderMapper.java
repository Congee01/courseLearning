package cn.seecoder.courselearning.mapperservice.order;


import cn.seecoder.courselearning.po.order.UserVipOrder;

public interface UserVipOrderMapper {
    int insert(UserVipOrder userVipOrder);

    int updateByPrimaryKey(UserVipOrder userVipOrder);

    UserVipOrder selectByPrimaryKey(Integer id);
}
