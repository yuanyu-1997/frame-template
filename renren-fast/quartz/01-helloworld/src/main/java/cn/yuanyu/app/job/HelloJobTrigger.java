package cn.yuanyu.app.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import java.text.SimpleDateFormat;
import java.util.Date;

// 定义任务类
public class HelloJobTrigger implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// 定义时间
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = dateFormat.format(date);
		
		// 定义工作任务内容
		System.out.println("进行数据库备份操作。当前任务执行的时间："+dateString);
		
		// 获取jobKey、startTime、endTime
		Trigger trigger = context.getTrigger();
		System.out.println("jobKey的标识:"+trigger.getJobKey().getName()+";jobKey的组名称："+trigger.getJobKey().getGroup());
		System.out.println("任务开始时间:"+dateFormat.format(trigger.getStartTime())+";任务结束时间："+dateFormat.format(trigger.getEndTime()));
	}
}