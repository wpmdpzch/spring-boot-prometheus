package com.wpm.prometheus.util;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wpm.prometheus.controller.GreetingController;

/**
 * @author pengmingwang
 *
 */
public class MetricUtil {
    public static MetricRegistry metrics = new MetricRegistry();

    public final static Timer timer = metrics
            .timer(MetricRegistry.name(GreetingController.class, "test the time form request the method"));
    public final static Counter counter = metrics
            .counter(MetricRegistry.name(GreetingController.class, "test the count func for request the method"));
    public final static Meter meter = metrics
            .meter(MetricRegistry.name(GreetingController.class, "test for meter func"));
    public final static Histogram hisogram = metrics
            .histogram(MetricRegistry.name(GreetingController.class, "test for hisogram"));
}
