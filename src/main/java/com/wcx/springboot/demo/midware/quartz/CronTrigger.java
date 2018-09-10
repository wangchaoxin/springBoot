package com.wcx.springboot.demo.midware.quartz;

import org.quartz.Trigger;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * CronTrigger is often more useful than SimpleTrigger, if you need a job-firing schedule that recurs based on calendar-like notions, rather than on the exactly specified intervals of SimpleTrigger.
 * With CronTrigger, you can specify firing-schedules such as “every Friday at noon”, or “every weekday and 9:30 am”, or even “every 5 minutes between 9:00 am and 10:00 am on every Monday, Wednesday and Friday during January”.
 * Even so, like SimpleTrigger, CronTrigger has a startTime which specifies when the schedule is in force, and an (optional) endTime that specifies when the schedule should be discontinued.
 */
public class CronTrigger {
    public static void main(String[] args) {

        //Build a trigger that will fire every other minute, between 8am and 5pm, every day:
        Trigger trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronSchedule("0 0/2 8-17 * * ?"))
                .forJob("myJob", "group1")
                .build();

    }
}
