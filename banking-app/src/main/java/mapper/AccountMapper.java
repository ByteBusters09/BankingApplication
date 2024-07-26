package mapper;

import dto.AccountDto;
import entity.Account;

public class AccountMapper {

//    here we converted accountDTO into a accountJPA Entity
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account (
                accountDto.getId (),
                accountDto.getAccount_holder_name (),
                accountDto.getBalance ()
        );
        return account;
    }

//    convert accountJPA Entity into accountDTO

    public static AccountDto mapToAccountDTO(Account account){

        AccountDto accountDto = new AccountDto (
                account.getId (),
                account.getAccountHolderName (),
                account.getBalance ()
        );

        return accountDto;
    }
}
