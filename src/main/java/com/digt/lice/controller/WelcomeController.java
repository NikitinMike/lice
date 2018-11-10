package com.digt.lice.controller;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.model.Token;
import com.digt.lice.repositories.AccountRepositories;
import com.digt.lice.repositories.LicenseRepository;
import com.digt.lice.repositories.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@AllArgsConstructor
public class WelcomeController {

    private AccountRepositories accountRepositories;
    private LicenseRepository licenseRepository;
    private TokenRepository tokens;

    @RequestMapping({"/","home"})
    public String welcome() {
//        return "index";
        return "redirect:/accounts/";
    }

    @RequestMapping("/accounts")
    public String account(Model model) {
//        accountRepositories.findAll().forEach(a-> System.out.println(a.getLicenses()));
        model.addAttribute("accounts",accountRepositories.findAll());
        return "accounts";
    }

    @RequestMapping("/accounts/error")
    public String accountErroe(Model model) {
//        accountRepositories.findAll().forEach(a-> System.out.println(a.getLicenses()));
//        model.addAttribute("accounts",accountRepositories.findAll());
        return "error";
    }

    @RequestMapping("/accounts/new")
    public String accountNew(Model model) {
        accountRepositories.save(new Account("Misha"));
        model.addAttribute("accounts",accountRepositories.findAll());
        return "redirect:/accounts/";
    }

    @RequestMapping("/account/{accountNumber}/licenses/")
    public String licenseView(@PathVariable String accountNumber,Model model) {
        Account account = accountRepositories.findAccountByNumber(accountNumber);
        model.addAttribute("account",accountNumber);
        model.addAttribute("licenses",licenseRepository.findAllByAccount(account));
        return "licenses";
    }

    @RequestMapping("/account/{accountNumber}/licenses/new")
    public String license(@PathVariable String accountNumber,Model model) {
        Account account = accountRepositories.findAccountByNumber(accountNumber);
        model.addAttribute("account",accountNumber);
        licenseRepository.save(account.addLicense(new License((int) (Math.random()*10))));
        model.addAttribute("licenses",licenseRepository.findAllByAccount(account));
        return "licenses";
    }

    @RequestMapping("/account/{accountNumber}/license/{id}/delete")
    public String licenseDel(@PathVariable String accountNumber,Model model,@PathVariable Long id) {
        licenseRepository.delete(id);
        return "redirect:/account/"+accountNumber+"/licenses/";
    }

    @RequestMapping("/account/{accountNumber}/delete")
    public String accountDel(@PathVariable String accountNumber,Model model) {
        Account account = accountRepositories.findAccountByNumber(accountNumber);
        if(account.getLicenses().size()>0) return "redirect:/accounts/error";
        else accountRepositories.delete(account.getId());
        return "redirect:/accounts/";
    }

    @RequestMapping("/license/{id}/tokens")
//    @ResponseBody public List<Token> tokens(Model model,@PathVariable Long id) {
    public String tokens(Model model,@PathVariable Long id) {
        License license = licenseRepository.findAllById(id);
//        System.out.println(license.getExpire());
        model.addAttribute("license",license);
        model.addAttribute("tokens",tokens.findAllByLicense(license));
        return "tokens";
    }

    @RequestMapping("/license/{id}/newtoken")
    public String tokenNew(Model model,@PathVariable Long id) {
        License license=licenseRepository.findAllById(id);
        if(license.allowIsuueTokens()) tokens.save(new Token(license));
        return "redirect:/license/"+id+"/tokens";
    }

    @RequestMapping("/license/{id}/token/{tokenId}/delete")
    public String tokenDel(@PathVariable Long id,@PathVariable Long tokenId) {
        tokens.delete(tokenId);
        return "redirect:/license/"+id+"/tokens";
    }

}
