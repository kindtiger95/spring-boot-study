package springbasicpractice.common.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@EnableAsync
@Configuration
public class SpringAsyncConfiguration {

    @Bean
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10); // 기본 스레드 수
        taskExecutor.setMaxPoolSize(10); // 최대 스레드 수
//        taskExecutor.setQueueCapacity(10000); // Queue 사이즈
        taskExecutor.setRejectedExecutionHandler(new CallerRunsPolicy());
//        taskExecutor.setWaitForTasksToCompleteOnShutdown(true); //application 종료 시 Queue 모든 작업 완료 후 종료
        taskExecutor.setThreadNamePrefix("AsyncExecutor-");
        taskExecutor.initialize();
        return taskExecutor;
    }

}
