package com.scheduler;

import java.util.List;

public interface Scheduler {

    void addTasks(List<Task> tasks);

    void run();

    void printTimeline();

}
