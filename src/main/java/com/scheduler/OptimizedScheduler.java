package com.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OptimizedScheduler extends AbstractScheduler {
    @Override
    protected void scheduleAvailable(int now) {
        int used = running.stream().mapToInt(r -> r.task().cpusNeeded()).sum();
        int free = CPU_COUNT - used;
        List<Task> avail = new ArrayList<>();
        for (Task t : tasks) {
            if (t.remaining() > 0 && running.stream().noneMatch(r -> r.task() == t)) {
                avail.add(t);
                }
        }

        // primeira vez - vai preencher com os maiores para os menores
        avail.sort(Comparator.comparingInt((Task task) -> -task.cpusNeeded()));
        List<Task> skipped = new ArrayList<>();
        for (Task task : avail) {
            if (task.cpusNeeded() <= free) {
                running.add(new Running(task, now));
                free -= task.cpusNeeded();
            } else {
                skipped.add(task);
            }
        }

        // segunda passada - garantir que nao tem nenhum gap, preenchendo os gaps com os menores
        skipped.sort(Comparator.comparingInt(Task::cpusNeeded));
        for (Task task : skipped) {
            if (task.cpusNeeded() <= free) {
                running.add(new Running(task, now));
                free -= task.cpusNeeded();
            }
        }
    }
}
