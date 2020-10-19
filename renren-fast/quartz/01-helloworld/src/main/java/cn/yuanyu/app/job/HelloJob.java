package cn.yuanyu.app.job;


import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yuanyu
 */
public class HelloJob implements Job {
    public HelloJob() {
        System.out.println("cn.yuanyu.app.job.HelloJob.HelloJob...");
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 工作内容
        String formatTime = simpleDateFormat.format(new Date());
        System.out.println("正在进行数据的备份工作，备份数据库的时间是: " + formatTime);

        // 获取JobDetail的内容
        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        System.out.println("工作任务的名称=" + jobKey.getName() + ",工作任务的组=" + jobKey.getGroup());
        System.out.println("任务类的名称（带路径）=" + jobExecutionContext.getJobDetail().getJobClass().getName());
        System.out.println("任务类的名称（简单）=" + jobExecutionContext.getJobDetail().getJobClass().getSimpleName());
        // 从JobDetail对象中获取JobDataMap的数据
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobDataMessage = jobDataMap.getString("message");
        System.out.println("任务数据的参数值: " + jobDataMessage);

        // 获取Trigger的内容
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        System.out.println("触发器名称=" + triggerKey.getName() + ",触发器数组=" + triggerKey.getGroup());
        // 从Trigger对象中获取JobDataMap的数据
        JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        String triggerDataMessage = triggerDataMap.getString("message");
        System.out.println("触发器数据的参数值: " + triggerDataMessage);

        // 获取其他的内容
        try {
            Thread.sleep(1000);
            System.out.println("当前时间: " + simpleDateFormat.format(new Date()));
        } catch (InterruptedException ignored) {}

        String fireTime = simpleDateFormat.format(jobExecutionContext.getFireTime());
        String nextFireTime = simpleDateFormat.format(jobExecutionContext.getNextFireTime());
        System.out.println("当前任务的执行时间: " + fireTime);
        System.out.println("下一次任务的执行时间: " + nextFireTime);

        System.out.println();
    }
}
