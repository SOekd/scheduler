package com.scheduler;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Task> list = Arrays.asList(
                new Task("T1",10,1,20),
                new Task("T2",20,4,60),
                new Task("T3",15,2,40),
                new Task("T4",20,2,30),
                new Task("T5",10,1,40),
                new Task("T6",15,2,50),
                new Task("T7",10,4,60),
                new Task("T8",15,1,20),
                new Task("T9",10,2,40),
                new Task("T10",20,1,30)
        );

        System.out.println("--- Versao Padrao ---");
        Scheduler simple = new SimpleScheduler();
        simple.addTasks(list);
        simple.run();
        simple.printTimeline();

        System.out.println("\n--- Versao Otimizada ---");
        Scheduler opt = new OptimizedScheduler();
        opt.addTasks(list);
        opt.run();
        opt.printTimeline();

    }
}