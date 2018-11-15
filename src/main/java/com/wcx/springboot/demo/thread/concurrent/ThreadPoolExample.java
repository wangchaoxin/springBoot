package com.wcx.springboot.demo.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //1.Factory Methods of the Executors Class
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //2.Directly Create an ExecutorService
        ExecutorService executorService =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>());

        //3.Assigning Tasks to the ExecutorService
        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        //excute the runnable
        executorService.execute(runnableTask);
        //excute the callbale
        Future<String> future =
                executorService.submit(callableTask);
        //invokeAny() assigns a collection of tasks to an ExecutorService, causing each to be executed,
        // and returns the result of a successful execution of one task (if there was a successful execution).
        String result = executorService.invokeAny(callableTasks);

        //nvokeAll() assigns a collection of tasks to an ExecutorService, causing each to be executed,
        // and returns the result of all task executions in the form of a list of objects of type Future.
        List<Future<String>> futures = executorService.invokeAll(callableTasks);

        //4.Shutting Down an ExecutorService
        /*The shutdown() method doesn’t cause an immediate destruction of the ExecutorService.
         It will make the ExecutorService stop accepting new tasks and shut down after all running
         threads finish their current work.*/
        executorService.shutdown();

        /*The shutdownNow() method tries to destroy the ExecutorService immediately,
         but it doesn’t guarantee that all the running threads will be stopped at the same time.
         This method returns a list of tasks which are waiting to be processed.*/
        List<Runnable> notExecutedTasks = executorService.shutdownNow();

        /*There is also another method awaitTermination(long timeout, TimeUnit unit) which forcefully blocks
        until all tasks have completed execution after a shutdown event triggered or execution-timeout occurred,
        or the execution thread itself is interrupted,*/
        executor.awaitTermination(20l, TimeUnit.NANOSECONDS);

        /*One good way to shut down the ExecutorService (which is also recommended by Oracle) is to
        use both of these methods combined with the awaitTermination() method. With this approach,
        the ExecutorService will first stop taking new tasks, the wait up to a specified period of time
        for all tasks to be completed. If that time expires, the execution is stopped immediately:*/
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        //5. The Future Interface
        /*The Future interface provides a special blocking method get() which returns an actual result of the
        Callable task’s execution or null in the case of Runnable task. Calling the get() method while the task
        is still running will cause execution to block until the task is properly executed and the result is available.*/
        Future<String> future1 = executorService.submit(callableTask);
        String result1 = null;
        try {
            result1 = future1.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //block for a period
        String result2 = future1.get(200, TimeUnit.MILLISECONDS);

        /*If the execution period is longer than specified (in this case 200 milliseconds), a TimeoutException will be thrown.
        The isDone() method can be used to check if the assigned task is already processed or not.
        The Future interface also provides for the cancellation of task execution with the cancel() method, and to check the cancellation with isCancelled() method:*/
        boolean canceled = future.cancel(true);
        boolean isCancelled = future.isCancelled();

        // if the future result is ready and fetch the data if the computation is done:
        if (future.isDone() && !future.isCancelled()) {
            future.get();
        }


        //6. The ScheduledExecutorService Interface
        //The ScheduledExecutorService runs tasks after some predefined delay and/or periodically
        //To schedule a single task’s execution after a fixed delay, us the scheduled() method of the ScheduledExecutorService
        //the code below delays for one second before executing callableTask
        ScheduledExecutorService executorService1 = Executors
                .newSingleThreadScheduledExecutor();
        Future<String> resultFuture =
                executorService1.schedule(callableTask, 1, TimeUnit.SECONDS);

        /*The following block of code will execute a task after an initial delay of 100 milliseconds,
         and after that, it will execute the same task every 450 milliseconds. If the processor needs more time
         to execute an assigned task than the period parameter of the scheduleAtFixedRate() method,
         the ScheduledExecutorService will wait until the current task is completed before starting the next:*/
        Future<String> resultFuture1 = (Future<String>) executorService1
                .scheduleAtFixedRate(runnableTask, 100, 450, TimeUnit.MILLISECONDS);

        /*If it is necessary to have a fixed length delay between iterations of the task,
        scheduleWithFixedDelay() should be used. For example, the following code will guarantee a 150-millisecond pause
        between the end of the current execution and the start of another one.*/
        executorService1.scheduleWithFixedDelay(runnableTask, 100, 150, TimeUnit.MILLISECONDS);


    }
}
