package service;
import dto.AccountDto;
import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long Id);
    AccountDto deposit(Long id, double account);
    AccountDto withdraw(Long id, double account);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
}
