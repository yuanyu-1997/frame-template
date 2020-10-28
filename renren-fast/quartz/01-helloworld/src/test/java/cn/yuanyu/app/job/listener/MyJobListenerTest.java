package cn.yuanyu.app.job.listener;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

public class MyJobListenerTest {
    public static void main(String[] args) throws Exception {
        // 1：从工厂中获取任务调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2：定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail job = JobBuilder.newJob(HelloJobListener.class)
                .withIdentity("job1", "group1") // 定义该实例唯一标识
                .build();
        // 3：定义触发器 ，马上执行, 然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1") // 定义该实例唯一标识
                .startNow()  // 马上执行
                //.startAt(triggerStartTime) // 针对某个时刻执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .repeatSecondlyForever(5)) // 每5秒执行一次
                .build();

        // 4：使用触发器调度任务的执行
        scheduler.scheduleJob(job, trigger);

        // 创建并注册一个全局的Job Listener
        scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

        // 创建并注册一个指定任务的Job Listener
        // scheduler.getListenerManager().addJobListener(new MyJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("job1", "group1")))；
        // 5：开启
        scheduler.start();
        // 关闭
        // scheduler.shutdown();
    }
}