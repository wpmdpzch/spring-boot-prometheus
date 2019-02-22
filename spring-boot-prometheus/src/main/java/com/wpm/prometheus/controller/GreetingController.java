package com.wpm.prometheus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.Timer;
import com.wpm.prometheus.util.MetricUtil;

@RestController
public class GreetingController {

    @GetMapping("/g1")
    String greet1() {

        getTimer();

        return "Hello g1!";
    }

    @GetMapping("/g2")
    String greet2() {

        getCounter();

        return "Hello g2!";
    }

    @GetMapping("/g3")
    String greet3() {

        getMeter();

        return "Hello g3!";
    }

    @GetMapping("/g4")
    String greet4() {

        getHistogram();

        return "Hello g4!";
    }

    void getTimer() {
        Timer.Context bbbContext = MetricUtil.timer.time();
        try {
            // do something...
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bbbContext.stop();
    }

    void getCounter() {
        MetricUtil.counter.inc();
    }

    void getMeter() {
        MetricUtil.meter.mark();
    }

    void getHistogram() {

        MetricUtil.hisogram.update((int) MetricUtil.timer.getSnapshot().getMax());
    }
}
