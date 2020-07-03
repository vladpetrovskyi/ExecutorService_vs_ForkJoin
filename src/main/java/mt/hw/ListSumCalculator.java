package mt.hw;

import java.util.List;

public interface ListSumCalculator {
    Long calculate(List<Integer> list, int threadsNumber) throws InterruptedException;
}
