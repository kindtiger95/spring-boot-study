package springbasicpractice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async("threadPoolTaskExecutor")
    public void asyncTestFunc1() {

        long beforeTime = System.currentTimeMillis();
        System.out.println("AsyncFunc1 is Started.");
        while (System.currentTimeMillis() - beforeTime < 5000L) {
        }
        log.info("AsyncFunc1 is Done.");
    }

    @Async("threadPoolTaskExecutor")
    public void asyncTestFunc2() {

        long beforeTime = System.currentTimeMillis();
        System.out.println("AsyncFunc2 is Started.");
        while (System.currentTimeMillis() - beforeTime < 5000L) {
        }
        log.info("AsyncFunc2 is Done.");
    }
}
