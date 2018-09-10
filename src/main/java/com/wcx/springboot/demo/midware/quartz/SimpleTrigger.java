package com.wcx.springboot.demo.midware.quartz;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class SimpleTrigger {
    public static void main(String[] args) throws SchedulerException {
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        // define the job and tie it to our MyJob class
        JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .usingJobData("name", "wang")  //JobDataMap:可以向其中添加数据，在Job中可以取到
                .build();

        // Trigger the job to run now, and then repeat every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
//                .startAt(futureDate(5, DateBuilder.IntervalUnit.MINUTE)) // use DateBuilder to create a date in the future
//                .startAt().endAt()
                .withSchedule(simpleSchedule()   //the properties of a SimpleTrigger include: a start-time, and end-time, a repeat count, and a repeat interval.
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(job, trigger);





        // and start it off
        scheduler.start();
    }
}
