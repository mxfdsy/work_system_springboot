package com.coding.commons.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by 平凡的世界 on 2018/5/6.
 */
public interface BaseJob extends Job {
    @Override
    void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException;
}
