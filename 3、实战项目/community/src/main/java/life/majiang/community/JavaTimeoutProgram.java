package life.majiang.community;

import java.util.concurrent.*;

/**
 * Java实现任务执行超时自动取消功能的3种方式
 */
public class JavaTimeoutProgram {

    // 耗时的方法
    public static Object longRunningMethod() {
        for (int i = 0; i < 10; i++) {
            System.out.println("这是第" + (i + 1) + "次打印");
            try {
                //等待1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("接收到中断异常，这里执行优雅中断操作");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("接收到异常，这里执行优雅异常处理操作");
                return null;
            }
            System.out.println("haha");
        }
        return "完成";
    }

    // 超时时间为3s
    private static final int TIMEOUT = 3000;

    /**
     * 创建线程池，用于执行通过CompletionService.submit提交的Callable任务，并返回一个Future对象
     * 通过CompletionService.poll设定执行超时时间，根据超时返回处理结果判断任务是否已执行结束
     */
    public static void ThreadPoolSolution() {
        // 创建只有一个线程的线程池
        Executor executor = Executors.newSingleThreadExecutor();

        // 创建一个新的CompletionService，用于监控执行时间
        CompletionService<Object> completionService = new ExecutorCompletionService<>(executor);
        // 提交要执行的方法
        Future<?> future = completionService.submit(() -> {
            return longRunningMethod();
        });

        try {
            Object result = completionService.poll(TIMEOUT, TimeUnit.MILLISECONDS);
            if (result == null) {
                System.out.println("超时了，结束该方法的执行");
                future.cancel(true);
            } else {
                // 方法执行完毕，处理执行结果
                System.out.println("方法执行完毕，结果：" + result.toString());
            }
        } catch (InterruptedException e) {
            System.out.println("出现异常，结束该方法的执行");
            future.cancel(true);
        }
        return ;
    }

    public static void FutureTaskSolution() {
        System.out.println("FutureTaskSolution Start to execute...");
        // 创建一个FutureTask
        FutureTask<Object> futureTask = new FutureTask<Object>(() -> {
            return longRunningMethod();
        });

        // 提交要执行的FutureTask
        new Thread(futureTask).start();

        // 获取执行结果
        try {
            Object result = futureTask.get(TIMEOUT, TimeUnit.MILLISECONDS);
            System.out.println("方法执行完毕，结果：" + result.toString());
        } catch (InterruptedException e) {
            System.out.println("出现异常，结束该方法的执行");
            futureTask.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("出现异常，结束该方法的执行");
            futureTask.cancel(true);
        } catch (TimeoutException e) {
            // 超时了，结束该方法的执行
            System.out.println("超时了，结束该方法的执行");
            futureTask.cancel(true);
        }

    }

    public static void CompletableFutureSolution() {
        System.out.println("CompletableFutureSolution Start to execute...");

        // 创建一个新的CompletableFuture
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            // 这里是要执行的方法
            return longRunningMethod();
        });

        // 获取执行结果
        try {
            Object result = future.get(TIMEOUT, TimeUnit.MILLISECONDS);
            System.out.println("方法执行完毕，结果：" + result.toString());
        } catch (InterruptedException e) {
            System.out.println("出现异常，结束该方法的执行");
            future.cancel(true);
        } catch (ExecutionException e) {
            System.out.println("出现异常，结束该方法的执行");
            future.cancel(true);
        } catch (TimeoutException e) {
            // 超时了，结束该方法的执行
            System.out.println("超时了，结束该方法的执行");
            future.cancel(true);
        }
    }

    public static void main(String[] args) {
        // 方式1
        ThreadPoolSolution();

        // 方式2
        //FutureTaskSolution();

        // 方式3
        //CompletableFutureSolution();
    }

}
