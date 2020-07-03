package mt.hw.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import mt.hw.ListSumCalculator;

public class ExecutorServiceCalculator implements ListSumCalculator {

    @Override
    public Long calculate(List<Integer> list, int threadsNumber) throws InterruptedException {
        List<CalculatorCallableTask> calculatorTasks = new ArrayList<>();

        for (int i = 0; i < threadsNumber; i++) {
            calculatorTasks.add(new CalculatorCallableTask(list, i, threadsNumber));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        List<Future<Integer>> executorServiceResult = executorService.invokeAll(calculatorTasks);

        long sum = 0;
        try {
            executorService.shutdown();
            for (Future<Integer> future : executorServiceResult) {
                sum += future.get();
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        return sum;
    }
}
