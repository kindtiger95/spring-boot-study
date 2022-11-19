package springbasicpractice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springbasicpractice.dto.TestDTO.AsyncResponse;
import springbasicpractice.service.AsyncService;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping
    public AsyncResponse asyncTest() {
        asyncService.asyncTestFunc1();
        asyncService.asyncTestFunc2();
        return AsyncResponse.builder()
                            .code("test")
                            .message("return!")
                            .build();
    }
}
