package com.start.bike;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Component
public class ApiCaller {

    private static final Logger logger = LoggerFactory.getLogger(ApiCaller.class);

    private WebClient webClient;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.retry.attempts:3}")
    private int retryAttempts;

    @Value("${api.retry.delay:1000}")
    private int retryDelay; // in milliseconds

    @PostConstruct
    public void init() {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    /**
     * 调用外部API，并根据配置进行重试
     *
     * @param endpoint API端点
     * @param responseType 响应类型
     * @param <T> 泛型响应类型
     * @return Mono<T> 返回Mono包装的对象
     */
    public <T> Mono<T> callApiWithRetry(String endpoint, Class<T> responseType) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(responseType)
                .retryWhen(retrySpec());
    }

    private reactor.util.retry.Retry retrySpec() {
        return reactor.util.retry.Retry.fixedDelay(retryAttempts, Duration.ofMillis(retryDelay))
                .filter(throwable -> throwable instanceof Exception)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> retrySignal.failure());
    }
}