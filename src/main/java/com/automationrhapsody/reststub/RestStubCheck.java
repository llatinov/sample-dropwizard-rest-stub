package com.automationrhapsody.reststub;

import com.automationrhapsody.reststub.persistence.PersonDB;
import com.codahale.metrics.health.HealthCheck;

public class RestStubCheck extends HealthCheck {
    private final String version;

    public RestStubCheck(String version) {
        this.version = version;
    }

    @Override
    protected Result check() throws Exception {
        if (PersonDB.getCount() == 0) {
            return Result.unhealthy("No persons in DB! Version: " + this.version);
        }
        return Result.healthy("OK with version: " + this.version + ". Persons count: " + PersonDB.getCount());
    }
}
