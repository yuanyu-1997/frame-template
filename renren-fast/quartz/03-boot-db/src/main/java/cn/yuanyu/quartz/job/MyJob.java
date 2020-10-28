package cn.yuanyu.quartz.job;


import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

// QuartzAutoConfiguration

/**
 * QRTZ_JOB_DETAILS
 * QRTZ_SIMPLE_TRIGGERS
 */

// https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#boot-features-quartz
/**
 * @author yuanyu
 */
@Slf4j
public class MyJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("rush...");
    }
}
