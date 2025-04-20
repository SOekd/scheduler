package com.scheduler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractScheduler implements Scheduler {

    protected final int CPU_COUNT = 4;

    protected List<Task> tasks;

    protected List<Running> running = new ArrayList<>();

    protected List<List<String>> timeline = new ArrayList<>();

    @Override
    public void addTasks(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        for (Task task : tasks) {
            this.tasks.add(task.copy());
        }
    }

    @Override
    public void run() {
        int now = 0;
        scheduleAvailable(now);

        while (!running.isEmpty()) {
            int next = running.stream().mapToInt(Running::endTime).min().getAsInt();

            // fatias de 5s
            for (int t = now; t < next; t += 5) {
                List<String> slice = new ArrayList<>();
                for (Running runningTask : running) {
                    for (int c = 0; c < runningTask.task().cpusNeeded(); c++) {
                        slice.add(runningTask.task().id());
                    }
                }

                // gap de cpu pra identificar dps
                while (slice.size() < CPU_COUNT) {
                    slice.add("NA");
                }

                timeline.add(slice);
            }
            now = next;

            // atualiza término de quantums e tarefas
            Iterator<Running> it = running.iterator();
            while (it.hasNext()) {
                Running runningTask = it.next();
                if (runningTask.endTime() == now) {

                    Task task = runningTask.task();

                    task.remaining(task.remaining() - runningTask.runLength());
                    it.remove();
                }
            }
            scheduleAvailable(now);
        }
        System.out.println("Tempo total: " + now + " s");
    }

    @Override
    public void printTimeline() {
        for (int cpu = 0; cpu < CPU_COUNT; cpu++) {
            System.out.print("cpu" + cpu + ": [");
            for (int i = 0; i < timeline.size(); i++) {
                System.out.print(timeline.get(i).get(cpu));
                if (i < timeline.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }

    protected abstract void scheduleAvailable(int now);

}
