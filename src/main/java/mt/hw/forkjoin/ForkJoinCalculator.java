package mt.hw.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import mt.hw.ListSumCalculator;

public class ForkJoinCalculator implements ListSumCalculator {
    @Override
    public Long calculate(List<Integer> list, int threadsNumber) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        return forkJoinPool.invoke(new CalculatorRecursiveTask(list, threadsNumber));
    }
}
