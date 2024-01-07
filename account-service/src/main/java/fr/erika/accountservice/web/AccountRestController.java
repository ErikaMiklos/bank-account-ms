package fr.erika.accountservice.web;

import fr.erika.accountservice.clients.CustomerRestClient;
import fr.erika.accountservice.entities.BankAccount;
import fr.erika.accountservice.model.Customer;
import fr.erika.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> bankAccountList(){
        List<BankAccount> accountList = bankAccountRepository.findAll();
        accountList.forEach(account -> {
            account.setCustomer(customerRestClient.findCustomerById(account.getCustomerId()));
        });
        return accountList;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);
        return bankAccount;
    }

}
