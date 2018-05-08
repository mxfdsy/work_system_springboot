//package com.coding.attend.service;
//
//import com.coding.attend.dao.AttendMapper;
//import com.coding.attend.entity.Attend;
//import com.coding.commons.utils.DateUtils;
//import org.apache.shiro.util.CollectionUtils;
//import org.codehaus.plexus.component.annotations.Component;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by 没想法的岁月 on 2018/5/8.
// */
//@Service
//public class ScheduledTimer {
//    private static final int ABSENCE_DAY = 480;
//    /**
//     * 2 代表缺勤
//     * 1 代表正常
//     */
//    private static final byte ATTEDN_STATUS_ABNORMAL = 2;
//    private static final byte ATTEDN_STATUS_NORMAL = 1;
//
//    @Autowired
//    private AttendMapper attendMapper;
//
//
//    @Scheduled(cron = "0 0 23 * * ?")
//    public void checkAttend() {
//        System.out.println("我输出了" + new Date());
//        //查询今天都没打卡人数的自动插入他们的考勤数据置为缺勤，缺勤时长设置为480分钟
//        List<Long> userIdList = attendMapper.selectAbsenceTotalDay();
//        if (!CollectionUtils.isEmpty(userIdList)) {
//            ArrayList<Attend> attendList = new ArrayList<>();
//            for (Long userId : userIdList
//                    ) {
//                Attend attend = new Attend();
//                attend.setUserId(userId);
//                attend.setAttendDate(new Date());
//                attend.setAttendWeek((byte) DateUtils.getTodayWeek());
//                attend.setAbsence(ABSENCE_DAY);
//                attend.setAttendStatus(ATTEDN_STATUS_ABNORMAL);
//                attendList.add(attend);
//            }
//            //批量插入
//            attendMapper.batchInsert(attendList);
//        }
//
//        //检查晚打卡 将下班未打卡的记录设置为异常
//        List<Attend> absencelist = attendMapper.selectTodayEveningAbsence();
//        if (!CollectionUtils.isEmpty(absencelist)) {
//            for (Attend attend : absencelist) {
//                attend.setAbsence(ABSENCE_DAY);
//                attend.setAttendStatus(ATTEDN_STATUS_ABNORMAL);
//                //由于每个人情况不一致，无法进行批量更新
//                attendMapper.updateByPrimaryKeySelective(attend);
//            }
//        }
//    }
//}
