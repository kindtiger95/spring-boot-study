package practice.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDto {
    private String username;
    private Integer age;

    @QueryProjection
    public MemberDto(String username, Integer age) {
        this.username = username;
        this.age = age;
    }
}
