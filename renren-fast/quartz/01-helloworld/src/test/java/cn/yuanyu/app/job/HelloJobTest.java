package cn.yuanyu.app.job;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloJobTest {

    public static void main(String[] args) throws SchedulerException {
        // 1、调度器（Scheduler），从工厂中获取任务调度的实例（默认：实例化StdSchedulerFactory）
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // 2、任务实例（JobDetail），定义一个任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)   // 加载任务类，与HelloJob完成绑定，要求HelloJob实现Job接口
                .withIdentity("job1", "group1")       // 参数一：任务的名称（唯一实例）、参数二：任务组的名称
                .usingJobData("message","打印日志")   // 传递参数，名称message
                .build();
        System.out.println("名称: " + jobDetail.getKey().getName());
        System.out.println("组名称: " + jobDetail.getKey().getGroup());
        System.out.println("任务类: " + jobDetail.getJobClass().getName());

        // 3、触发器（Trigger），定义触发器，马上执行，然后每5秒重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")                                // 参数一：触发器的名称（唯一实例）、参数二：触发器组的名称，key的名称必须指定，如果没用指定组名: DEFAULT
                .startNow()                                                                    // 马上执行
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5)) // 每5秒执行一次
                .usingJobData("message","simple触发器")                            // 传递参数，名称message
                .build();

        // 4、让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail, trigger);

        // 5、启动
        scheduler.start();

        // 6、停止
        // scheduler.shutdown();
    }


}