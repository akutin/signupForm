package name.alexey.register.rest;

import name.alexey.register.persistence.Account;
import name.alexey.register.service.AccountService;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/api/account")
class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Account create(@RequestBody @NotNull @Valid Account account) {
        return accountService.create( account);
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account get(@NotEmpty @PathVariable( value="username") String username) {
        return accountService.load( username);
    }
}
