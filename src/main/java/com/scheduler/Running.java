package com.scheduler;

import java.util.Objects;

public class Running {

    private final Task task;
    private final int endTime;
    private final int runLength;

    Running(Task task, int now) {
        this.task = task;
        this.runLength = Math.min(task.quantum(), task.remaining());
        this.endTime = now + runLength;
    }

    public Task task() {
        return task;
    }

    public int endTime() {
        return endTime;
    }

    public int runLength() {
        return runLength;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Running running = (Running) o;
        return endTime == running.endTime && runLength == running.runLength && Objects.equals(task, running.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, endTime, runLength);
    }

    @Override
    public String toString() {
        return "Running{" +
                "task=" + task +
                ", endTime=" + endTime +
                ", runLength=" + runLength +
                '}';
    }
}
