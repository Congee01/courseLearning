package cn.seecoder.courselearning.mapperservice.user;

import cn.seecoder.courselearning.po.user.UserVip;
import org.apache.ibatis.annotations.Param;

public interface UserVipMapper {
    int insert(UserVip userVip);

    UserVip selectByPrimaryKey(Integer id);

    void updateValid(@Param(value = "id") Integer id,@Param(value = "valid") Integer valid);
}
