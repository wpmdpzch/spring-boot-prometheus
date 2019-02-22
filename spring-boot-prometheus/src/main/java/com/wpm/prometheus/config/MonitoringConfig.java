package com.wpm.prometheus.config;

import java.util.Collection;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wpm.prometheus.export.CustomDropwizardExports;
import com.wpm.prometheus.util.MetricUtil;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;

@Configuration
class MonitoringConfig {


    @Bean
    SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> publicMetrics) {

        SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
        springBootMetricsCollector.register();

        return springBootMetricsCollector;
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {

        /*
         * Default Export is useful for jvm monitor, if you want to monitor the cpu and other index you can init it.
         */
        // DefaultExports.initialize();
        CollectorRegistry.defaultRegistry.register(new CustomDropwizardExports(MetricUtil.metrics));
        // you can config the servlet in web.xml also.
        return new ServletRegistrationBean(new MetricsServlet(), "/prometheus");
    }
}
