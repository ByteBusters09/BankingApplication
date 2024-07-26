package dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // it automatically generates constructor and getter setter methods
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String account_holder_name;
    private double balance;

}
