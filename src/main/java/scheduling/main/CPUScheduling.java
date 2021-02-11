package scheduling.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import scheduling.impl.ShortestJobFirstAlgo;
import scheduling.model.Process;

public class CPUScheduling {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process("P1", 2, 4));
        processes.add(new Process("P2", 10, 1));
        processes.add(new Process("P3", 3, 2));
        processes.add(new Process("P4", 2, 5));
        processes.add(new Process("P5", 0, 5));
        ShortestJobFirstAlgo sjf = new ShortestJobFirstAlgo();
        List<Process> scheduled = sjf.scheduleProcess(processes);
        AtomicInteger totalTurnaroundTime = new AtomicInteger();
        AtomicInteger totalWaitingTime = new AtomicInteger();
        scheduled.stream()
            .forEach(process -> {
                System.out.println(process);
                totalTurnaroundTime.addAndGet(process.getTurnAroundTime());
                totalWaitingTime.addAndGet(process.getWaitingTime());
            });
        System.out.println("Average turnaround Time : " + totalTurnaroundTime.get() / processes.size());
        System.out.println("Average Waiting Time : " + totalWaitingTime.get() / processes.size());
    }
}
