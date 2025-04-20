package com.scheduler;

import java.util.Objects;

public class Task {
    private final String id;
    private final int quantum;
    private final int cpusNeeded;
    private int remaining;

    public Task(String id, int quantum, int cpusNeeded, int totalDuration) {
        this.id = id;
        this.quantum = quantum;
        this.cpusNeeded = cpusNeeded;
        this.remaining = totalDuration;
    }

    public void remaining(int remaining) {
        this.remaining = remaining;
    }

    public String id() {
        return id;
    }

    public int quantum() {
        return quantum;
    }

    public int cpusNeeded() {
        return cpusNeeded;
    }

    public int remaining() {
        return remaining;
    }

    Task copy() {
        return new Task(id, quantum, cpusNeeded, remaining);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return quantum == task.quantum && cpusNeeded == task.cpusNeeded && remaining == task.remaining && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantum, cpusNeeded, remaining);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", quantum=" + quantum +
                ", cpusNeeded=" + cpusNeeded +
                ", remaining=" + remaining +
                '}';
    }
}