package com.wpm.prometheus.export;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Snapshot;

import io.prometheus.client.dropwizard.DropwizardExports;

public class CustomDropwizardExports extends DropwizardExports {
    /**
     * @param registry a metric registry to export in prometheus.
     */
    public CustomDropwizardExports(MetricRegistry registry) {
        super(registry);
    }


    /**
     * Export a histogram snapshot as a prometheus SUMMARY.
     *
     * @param dropwizardName
     *            metric name.
     * @param snapshot
     *            the histogram snapshot.
     * @param count
     *            the total sample count for this snapshot.
     * @param factor
     *            a factor to apply to histogram values.
     *
     */
    List<MetricFamilySamples> fromSnapshotAndCount(String dropwizardName, Snapshot snapshot, long count, double factor,
            String helpMessage) {
        String name = sanitizeMetricName(dropwizardName);
        List<MetricFamilySamples.Sample> samples = Arrays.asList(
                // min-max
                new MetricFamilySamples.Sample(name, Arrays.asList("value"), Arrays.asList("min"),
                        snapshot.getMin() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("value"), Arrays.asList("max"),
                        snapshot.getMax() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("value"), Arrays.asList("mean"),
                        snapshot.getMax() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("value"), Arrays.asList("stddev"),
                        snapshot.getStdDev() * factor),
                // quantile
                new MetricFamilySamples.Sample(name, Arrays.asList("quantile"), Arrays.asList("0.5"),
                        snapshot.getMedian() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("quantile"), Arrays.asList("0.75"),
                        snapshot.get75thPercentile() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("quantile"), Arrays.asList("0.95"),
                        snapshot.get95thPercentile() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("quantile"), Arrays.asList("0.98"),
                        snapshot.get98thPercentile() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("quantile"), Arrays.asList("0.99"),
                        snapshot.get99thPercentile() * factor),
                new MetricFamilySamples.Sample(name, Arrays.asList("quantile"), Arrays.asList("0.999"),
                        snapshot.get999thPercentile() * factor),
                new MetricFamilySamples.Sample(name + "_count", new ArrayList<String>(), new ArrayList<String>(),
                        count));
        return Arrays.asList(new MetricFamilySamples(name, Type.SUMMARY, helpMessage, samples));
    }
}

