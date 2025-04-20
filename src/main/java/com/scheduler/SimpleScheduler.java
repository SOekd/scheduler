package com.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SimpleScheduler extends AbstractScheduler {

    @Override
    protected void scheduleAvailable(int now) {

        int used = running.stream().mapToInt(runningTask -> runningTask.task().cpusNeeded()).sum();

        int free = CPU_COUNT - used;

        List<Task> avail = new ArrayList<>();
        for (Task task : tasks) {
            if (task.remaining() > 0 && running.stream().noneMatch(runningTask -> runningTask.task() == task)) {
                avail.add(task);
            }
        }

        avail.sort(Comparator.comparingInt((Task t) -> -t.quantum()));

        for (Task task : avail) {
            if (task.cpusNeeded() <= free) {
                running.add(new Running(task, now));
                free -= task.cpusNeeded();
            }
        }
    }


}
