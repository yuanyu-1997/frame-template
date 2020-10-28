package cn.yuanyu.app.job;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class HelloJobTriggerTest {

    public static void main(String[] args) throws Exception {
        // 1：从工厂中获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 定义日期
        Date startDate = new Date();
        // 启动任务，任务在当前时间3秒后执行
        startDate.setTime(startDate.getTime() + 3000);
        // 定义日期
        Date endDate = new Date();
        // 结束任务，任务在当前时间10秒后停止
        endDate.setTime(endDate.getTime() + 10000);

        // 2：定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJobTrigger.class)
                .withIdentity("job1", "group1") // 定义该实例唯一标识
                .usingJobData("message", "打印日志")
                .build();

        // 3：定义触发器 ，马上执行, 然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 定义该实例唯一标识
                .startAt(startDate)
                .endAt(endDate)
                // startNow() 马上执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatSecondlyForever(5)) // 每5秒执行一次
                .usingJobData("message", "simple触发器")
                .build();

        // 4：使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);

        // 5：开启
        scheduler.start();
        // 关闭
        // scheduler.shutdown();
    }
}