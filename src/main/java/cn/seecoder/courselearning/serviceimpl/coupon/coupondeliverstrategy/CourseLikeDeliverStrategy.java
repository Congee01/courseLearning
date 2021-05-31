package cn.seecoder.courselearning.serviceimpl.coupon.coupondeliverstrategy;

import cn.seecoder.courselearning.mapperservice.course.CourseLikesMapper;
import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.service.coupon.coupondeliverstrategy.CouponDeliverStrategy;
import cn.seecoder.courselearning.util.Constant;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 用户点赞数发放策略
 */
@Component
public class CourseLikeDeliverStrategy implements CouponDeliverStrategy {

    @Resource
    protected CourseLikesMapper courseLikesMapper;

    @Resource
    protected CourseMapper courseMapper;
    @Resource
    protected CourseOrderMapper courseOrderMapper;

    /**
     * 这里实现的逻辑为如果给extra中courseId对应的课程点过赞，则只要优惠券适用范围是该课程同学校的其他所有课程，则可以发放
     * @param user 用户
     * @param coupon 优惠券
     * @param extra 额外的判断数据
     * @return
     */
    @Override
    public boolean canDeliver(User user, Coupon coupon, Map<String, String> extra) {
        String courseIdStr = extra.get("courseId");
        System.out.println("courseId: "+extra.toString());
        if (courseIdStr.equals("")) return false;
        System.out.println("courseIdStr: "+ courseIdStr);
        Integer courseId = Integer.valueOf(courseIdStr);
        // 获取用户点赞数
        int result = courseLikesMapper.count(courseId,user.getId());
        if (result == 1) {
            // 判断优惠券的适用范围
            Course course = courseMapper.selectByPrimaryKey(courseId);
            Integer teacherId = course.getTeacherId();
            String schoolId = course.getSchool();
            List<Course> same_teacher = courseMapper.selectByTeacherId(teacherId);
            //同一老师
            for (Course c :
                    same_teacher) {
                if(!courseId.equals(c.getId()) && !isBought(c.getId(), user.getId()) && coupon.getScope().canUse(c, coupon))return true;
            }
            List<Course> same_school = courseMapper.selectBySchoolId(schoolId);
            //同一学校
            for (Course c :
                    same_school) {
                if(!courseId.equals(c.getId()) && !isBought(c.getId(), user.getId()) && coupon.getScope().canUse(c, coupon))return true;
            }
        }
        return false;
    }

    private boolean isBought(Integer courseId, Integer userId){
        List<CourseOrder> cs = courseOrderMapper.selectByUserId(userId);
        for (CourseOrder order :
                cs) {
            if(order.getCourseId().equals(courseId) && (order.getStatus().equals(Constant.ORDER_STATUS_WAIT) || order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS))){return true;}
        }
        return false;
    }
}
