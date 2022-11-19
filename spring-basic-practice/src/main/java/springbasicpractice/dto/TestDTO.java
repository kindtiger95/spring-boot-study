package springbasicpractice.dto;

import lombok.Builder;
import lombok.Getter;

public class TestDTO {

    @Getter
    @Builder
    public static class AsyncResponse {
        private String code;
        private String message;
    }
}
