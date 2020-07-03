package mt.hw;

import java.util.List;
import java.util.concurrent.TimeUnit;
import mt.hw.executor_service.ExecutorServiceCalculator;
import mt.hw.fork_join.ForkJoinCalculator;

public class Main {

    private static final int THREADS_NUMBER = 10;
    private static final int LIST_SIZE = 1_000_000;

    public static void main(String[] args) throws InterruptedException {
        List<Integer> intList = ListCreator.createList(LIST_SIZE);

        ExecutorServiceCalculator executorService = new ExecutorServiceCalculator();
        long startTimeExecutor = System.nanoTime();
        executorService.calculate(intList, THREADS_NUMBER);
        long finishTimeExecutor = System.nanoTime();
        long executorServiceExecutionTime =
                TimeUnit.NANOSECONDS.toMillis(finishTimeExecutor - startTimeExecutor);

        ForkJoinCalculator forkJoin = new ForkJoinCalculator();
        long startTimeFork = System.nanoTime();
        forkJoin.calculate(intList, THREADS_NUMBER);
        long finishTimeFork = System.nanoTime();
        long forkJoinExecutionTime = TimeUnit.NANOSECONDS.toMillis(finishTimeFork - startTimeFork);

        System.out.println("Executor Service = " + executorServiceExecutionTime
                + "; \nFork Join = " + forkJoinExecutionTime);
    }
}
