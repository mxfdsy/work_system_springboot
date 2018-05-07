package com.coding.attend.service;

import com.coding.attend.dao.AttendMapper;
import com.coding.attend.entity.Attend;
import com.coding.attend.vo.QueryCondition;
import com.coding.commons.page.PageQueryBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
    private static final int NOON_MUNITE =00 ;
    //日志的使用
    private Log log = LogFactory.getLog(AttendServicImp.class);

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Autowired
    private AttendMapper attendMapper;

    /**
     * 记录打卡
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

    /**
     * 通过记录的数据，检测缺勤情况
     */
    @Override
    @Transactional
    public void checkAttend() {
        //查询今天都没打卡人数的

        //只有早打卡的人或者晚打卡的人

        //早晚打卡时间不足8小时
        
    }
}
