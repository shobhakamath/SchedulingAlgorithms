package scheduling.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import scheduling.model.Process;
import scheduling.process.ScheduleProcess;

public class ShortestRemainingJobNext implements ScheduleProcess {
    @Override public List<Process> scheduleProcess(List<Process> processes) {
        AtomicInteger timeEnd = new AtomicInteger();
        Map<Integer, Process> map = processes.stream()
            .collect(Collectors.toMap(p -> p.getArrivalTime(), p -> p));
        return processes.stream()
            .sorted(Comparator.comparing(Process::getArrivalTime)
            .thenComparing(Process::getBurstTime))
             .map(process -> {
                    process.setResponseTime(timeEnd.get() - process.getArrivalTime());
                    timeEnd.addAndGet(process.getBurstTime());
                    process.setCompletionTime(timeEnd.get());
                    process.setTurnAroundTime(timeEnd.get() - process.getArrivalTime());
                    process.setWaitingTime(process.getTurnAroundTime() - process.getBurstTime());
                    return process;
                }
            )
            .collect(Collectors.toList());
    }
}
