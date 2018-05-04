package com.coding.attend.service;

import com.coding.attend.dao.AttendMapper;
import com.coding.attend.entity.Attend;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 平凡的世界 on 2018/5/3.
 */
@Service("AttendServicImp")
public class AttendServicImp implements AttendService {
    /**
     * 设置中午12点
     */
    private static final int NOON_TIME = 12;
    private static final int NOON_MUNITE =00 ;
    //日志的使用
    private Log log = LogFactory.getLog(AttendServicImp.class);

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Autowired
    private AttendMapper attendMapper;
    @Override
    public void signAttend(Attend attend) {
        try {
            Date today = new Date();
            attend.setAttendDate(today);
            attend.setAttendWeek((byte) com.coding.commons.utils.DateUtils.getTodayWeek());
            //获取中午12点的时间点
            Date noon = com.coding.commons.utils.DateUtils.getDate(NOON_TIME, NOON_MUNITE);
            //查询当天 此人有没有打卡记录
            Attend todayRecoder = attendMapper.selectTodaySignRecord(attend.getUserId());

            if (todayRecoder == null) {
                //比较当前时间中午12点时间
                if (today.compareTo(noon) <= 0) {
                    //早于上午打卡
                    attend.setAttendMoring(today);
                } else {
                    //晚上打卡
                    attend.setAttendEvening(today);
                }
                attendMapper.insertSelective(attend);
            } else {
                if (today.compareTo(noon) <= 0) {
                   return;
                } else {
                    //晚上打卡话则更新其打卡记录
                    todayRecoder.setAttendEvening(today);
                    attendMapper.updateByPrimaryKeySelective(todayRecoder);
                }
            }

        } catch (Exception e) {
            log.error("用户签到异常", e);
            //此处用户抛出异常是为了让事物回滚
            throw e;
        }
    }
}
