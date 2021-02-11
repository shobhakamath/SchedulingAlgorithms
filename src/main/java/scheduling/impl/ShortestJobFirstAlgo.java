package scheduling.impl;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import scheduling.model.Process;
import scheduling.process.ScheduleProcess;

public class ShortestJobFirstAlgo implements ScheduleProcess {
    public List<Process> scheduleProcess(List<Process> processes) {
        AtomicInteger timeEnd = new AtomicInteger();
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
