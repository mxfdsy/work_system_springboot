package com.coding.attend.service;

import com.coding.attend.dao.AttendMapper;
import com.coding.attend.entity.Attend;
import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;
import com.coding.commons.utils.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.util.CollectionUtils;
import org.apache.xbean.propertyeditor.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by 平凡的世界 on 2018/5/3.
 */
@Service("AttendServicImp")
public class AttendServicImp implements AttendService {
    /**
     * 设置中午12点
     */
    private static final int NOON_TIME = 12;
    private static final int NOON_MUNITE = 00;

    /**
     * 8小时的分钟数
     */
    private static final int ABSENCE_DAY = 480;
    /**
     * 2 代表缺勤
     * 1 代表正常
     */
    private static final byte ATTEDN_STATUS_ABNORMAL = 2;
    private static final byte ATTEDN_STATUS_NORMAL = 1;
    //日志的使用
    private Log log = LogFactory.getLog(AttendServicImp.class);

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Autowired
    private AttendMapper attendMapper;
    /**
     * 记录打卡
     *
     * @param attend
     */
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

    /**
     * 分页查询
     *
     * @param condition
     * @return
     */
    @Override
    public PageQueryBean listAttend(QueryCondition condition) {
        //如果没有查询到数据  我们就不用去查询调用查询sql去查
        int count = attendMapper.countByCondition(condition);
        PageQueryBean pageresult = new PageQueryBean();
        if (count > 0) {
            //这是准备返回给前端的信息
            pageresult.setTotalRows(count);
            pageresult.setCurrentPage(condition.getCurrentPage());
            pageresult.setPageSize(condition.getPageSize());
            //查询数据库
            List<Attend> attendList = attendMapper.selsectAttendPage(condition);
            pageresult.setItems(attendList);
        }
        return pageresult;
    }

    @Override
//    @Transactional
//    @Scheduled(cron = "0 9,25 15 * * ? *")
    public void checkAttend() {
        System.out.println("我输出了" + new Date());
        //查询今天都没打卡人数的自动插入他们的考勤数据置为缺勤，缺勤时长设置为480分钟
        List<Long> userIdList =attendMapper.selectAbsenceTotalDay();
        if (CollectionUtils.isEmpty(userIdList)) {
            ArrayList<Attend> attendList = new ArrayList<>();
            for (Long userId:userIdList
                 ) {
                Attend attend = new Attend();
                attend.setUserId(userId);
                attend.setAttendDate(new Date());
                attend.setAttendWeek((byte) DateUtils.getTodayWeek());
                attend.setAbsence(ABSENCE_DAY);
                attend .setAttendStatus(ATTEDN_STATUS_ABNORMAL);
                attendList.add(attend);
            }
            //批量插入
            attendMapper.batchInsert(attendList);
        }

        //检查晚打卡 将下班为打卡的记录设置为异常
        List<Attend> absencelist =attendMapper.selectTodayEveningAbsence();
        if (!CollectionUtils.isEmpty(absencelist)) {
            for (Attend attend : absencelist) {
                attend.setAbsence(ABSENCE_DAY);
                attend.setAttendStatus(ATTEDN_STATUS_ABNORMAL);
                //由于每个人情况不一致，无法进行批量更新
                attendMapper.updateByPrimaryKeySelective(attend);
            }
        }
    }
//    /**
//     * 通过记录的数据，检测缺勤情况
//     */
//    @Override
//    @Transactional
//    public void checkAttend() {
//        //查询今天都没打卡人数的自动插入他们的考勤数据置为缺勤，缺勤时长设置为480分钟
//        Attend attendresult = attendMapper.selectAbsenceTotalDay(attend);
//        Attend absentAttend = new Attend();
//        absentAttend.setUserId(attend.getUserId());
//        if (attendresult == null) {
////            该用户今天没打卡自动插入缺勤数据
//            attend.setAbsence(480);
//            attend.setAttendStatus((byte) 2);
//            attendMapper.insert(attend);
//        } else if ((attendresult.getAttendEvening() == null) || (attendresult.getAttendMoring() == null)) {
//            //只有早打卡的人或者晚打卡的人
//            attendresult.setAttendStatus((byte) 2);
//            attendMapper.updateByPrimaryKeySelective(attendresult);
//        } else {
//            //计算早晚时间差
//            Integer interval = DateUtils.getMunite(attendresult.getAttendMoring(), attendresult.getAttendEvening());
//            //判断时间差是否大于480
//            if (interval < INTERVAL) {
//                attendresult.setAbsence(interval);
//                attendresult.setAttendStatus((byte) 2);
//                attendMapper.updateByPrimaryKeySelective(attendresult);
//            } else {
//                attendresult.setAttendStatus((byte) 1);
//                attendMapper.updateByPrimaryKeySelective(attendresult);
//            }
//        }
//    }
}
