package edu.pwr.pizzeria.healthcheck;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "healthCheckClient", url = "${herokuUrl}")
public interface HealthCheckClient {

    @GetMapping("/v1/health-check")
    HealthCheck healthCheck();
}
