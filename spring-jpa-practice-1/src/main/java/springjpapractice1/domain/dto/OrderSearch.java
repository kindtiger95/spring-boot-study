package springjpapractice1.domain.dto;

import lombok.Getter;
import lombok.Setter;

import static springjpapractice1.commons.GlobalEnums.*;

@Getter
@Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
