package mt.hw.fork_join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CalculatorRecursiveTask extends RecursiveTask<Long> {
    private final List<Integer> intList;

    private static int threshold;

    public CalculatorRecursiveTask(List<Integer> intList, int threadsNumber) {
        this.intList = intList;
        CalculatorRecursiveTask.threshold = intList.size() / threadsNumber;
    }

    public CalculatorRecursiveTask(List<Integer> intList) {
        this.intList = intList;
    }

    @Override
    protected Long compute() {
        if (intList.size() > threshold) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(intList);
        }
    }

    private Collection<CalculatorRecursiveTask> createSubtasks() {
        List<CalculatorRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new CalculatorRecursiveTask(
                intList.subList(0, intList.size() / 2)));
        dividedTasks.add(new CalculatorRecursiveTask(
                intList.subList(intList.size() / 2, intList.size())));
        return dividedTasks;
    }

    private Long processing(List<Integer> intList) {
        return intList.stream()
                .reduce(0, Integer::sum)
                .longValue();
    }
}
