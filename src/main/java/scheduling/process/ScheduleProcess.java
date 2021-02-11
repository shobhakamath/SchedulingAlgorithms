package scheduling.process;

import java.util.List;
import scheduling.model.Process;

public interface ScheduleProcess {
    public List<Process> scheduleProcess(List<Process> processes);
}
