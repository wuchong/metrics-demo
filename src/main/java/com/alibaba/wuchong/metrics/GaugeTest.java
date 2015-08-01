package com.alibaba.wuchong.metrics;

import com.codahale.metrics.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * Created by wuchong on 15/7/29.
 */
public class GaugeTest {

    public static Queue<String> q = new LinkedList<String>();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);

        registry.register(MetricRegistry.name(GaugeTest.class, "queue", "size"), new Gauge<Integer>() {

            public Integer getValue() {
                return q.size();
            }
        });

        while(true){
            Thread.sleep(1000);
            q.add("Job-xxx");
        }
    }
}
