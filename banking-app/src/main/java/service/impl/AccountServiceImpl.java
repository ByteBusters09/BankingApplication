package service.impl;
import dto.AccountDto;
import entity.Account;
import mapper.AccountMapper;
import org.springframework.stereotype.Service;
import repository.AccountRepository;
import service.AccountService;
import java.util.List;
import java.util.stream.Collectors;

@Service // it will automatically create a spring beans for this class
public class AccountServiceImpl implements AccountService {

    // inject dependencies
    private AccountRepository accountRepository;


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
//        we need to convert account entity into accountJPA Entity and vice versa and
//        we will save this accountJPA entity into database
//        to do conversion we created a mapper class

        Account account = AccountMapper.mapToAccount (accountDto);
        Account savedAccount = accountRepository.save (account); // save will return the saved entity
        return AccountMapper.mapToAccountDTO (savedAccount);
    }


    @Override
    public AccountDto getAccountById(Long id) {
       Account account =  accountRepository
               .findById (id)
               .orElseThrow (()-> new RuntimeException ("Account does not exist"));
       return AccountMapper.mapToAccountDTO (account);
    }

    @Override
    public AccountDto deposit(Long id, double amount){
        Account account =  accountRepository
                .findById (id)
                .orElseThrow (()-> new RuntimeException ("Account does not exist"));
       double total =  account.getBalance() + amount;
       account.setBalance (total);
      Account savedAccount =  accountRepository.save (account);
      return AccountMapper.mapToAccountDTO (savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount){
        Account account =  accountRepository
                .findById (id)
                .orElseThrow (()-> new RuntimeException ("Account does not exist"));
        if (account.getBalance () < amount){
            throw  new RuntimeException ("Insufficient Amount");
        }

        double total = account.getBalance ()-amount;
        account.setBalance (total);
        Account savedAccount = accountRepository.save (account);
        return AccountMapper.mapToAccountDTO (savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll ();
        return accounts.stream ().map((account -> AccountMapper.mapToAccountDTO (account))).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id){
        Account account =  accountRepository
                .findById (id)
                .orElseThrow (()-> new RuntimeException ("Account does not exist"));
        accountRepository.deleteById (id);
    }
}
