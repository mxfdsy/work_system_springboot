package com.coding.attend.service;

import com.coding.attend.dao.AttendMapper;
import com.coding.attend.entity.Attend;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 平凡的世界 on 2018/5/3.
 */
@Service("AttendServicImp")
public class AttendServicImp implements AttendService {
    //日志的使用
    private Log log = LogFactory.getLog(AttendServicImp.class);

    @Autowired
    private AttendMapper attendMapper;
    @Override
    public void signAttend(Attend attend) {
        try {
            attendMapper.insertSelective(attend);
        } catch (Exception e) {
            log.error("用户签到异常",e);
            //此处用户抛出异常是为了让事物回滚
            throw e;
        }


    }
}
