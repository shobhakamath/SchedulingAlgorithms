package scheduling.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Process {
    private String processName;
    private int    arrivalTime;
    private int    burstTime;
    private int    completionTime;
    private int    turnAroundTime;
    private int    waitingTime;
    private int    responseTime;

    public Process(String processName, int arrivalTime, int burstTime) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

}
