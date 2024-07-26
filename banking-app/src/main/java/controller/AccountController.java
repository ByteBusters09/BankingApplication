package controller;
import dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AccountService;
import java.util.List;
import java.util.Map;

@RestController // this annotation makes this class as a springMVC REST controller class
@RequestMapping("/api/accounts") //base url for all the rest APIs that are defined under
// this controller class

public class AccountController {

    private  AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // create a add account REST API

    @PostMapping
    // RequestBody annotation will map the request body to this accountDTO(java object)
//    RequestBody basically contains JSON and it will automatically convert this JSON into the java object

   public  ResponseEntity<AccountDto> addAccount (@RequestBody AccountDto accountDto){
        return new ResponseEntity<> (accountService.createAccount (accountDto), HttpStatus.OK);
   }

   //get account REST API

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById (id);
        return ResponseEntity.ok (accountDto);
    }

//    deposit REST API
    @PutMapping("/{id}/deposit")
    // @PathVariable annotation is used to bind the {id} in the url in the Long id which is passed in arguments
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){
        Double amount = request.get ("amount");
       AccountDto accountDto = accountService.deposit (id, amount);
       return ResponseEntity.ok (accountDto);
    }

//    Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){
        double amount = request.get ("amount");
        AccountDto accountDto = accountService.withdraw (id, amount);
        return ResponseEntity.ok (accountDto);
    }

    // GetAllAccounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accounts = accountService.getAllAccounts ();
        return ResponseEntity.ok (accounts);
    }

    // Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount (id);
        return ResponseEntity.ok ("Account Deleted Successfully");
    }
}
