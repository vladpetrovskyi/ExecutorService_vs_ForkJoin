package mt.hw.executorservice;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculatorCallableTask implements Callable<Integer> {

    private final List<Integer> intList;
    private final int threadNumber;
    private final int batchSize;

    public CalculatorCallableTask(List<Integer> intList, int threadNumber, int threadsQuantity) {
        this.intList = intList;
        this.threadNumber = threadNumber;
        this.batchSize = intList.size() / threadsQuantity;
    }

    @Override
    public Integer call() {
        int firstElement = threadNumber * batchSize;
        int lastElement = firstElement + batchSize;
        int sum = 0;
        for (int i : intList.subList(firstElement, lastElement)) {
            sum += i;
        }
        return sum;
    }
}
