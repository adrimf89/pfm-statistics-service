package com.adri.pfm.statistics.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.adri.pfm.transaction")
public class PfmTransactionConfig {
    private String transactionStatusQueue;
}
