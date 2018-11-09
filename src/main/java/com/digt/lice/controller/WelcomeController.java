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
//        ar.save(new Account("Misha"));
//        ar.findAll().forEach((a)->System.out.println(a));
        return "index";
    }

    @RequestMapping("/accounts")
    public String account(Model model) {
        model.addAttribute("accounts",accountRepositories.findAll());
        return "accounts";
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
//        System.out.println("["+account+"]");
//        licenseRepository.findAll().forEach((l)-> System.out.println(l));
//        licenseRepository.findAllByAccount(account).forEach((a)->System.out.println(a));
        model.addAttribute("account",accountNumber);
//        model.addAttribute("licenses",account.getLicenses());
        model.addAttribute("licenses",licenseRepository.findAllByAccount(account));
        return "licenses";
    }

    @RequestMapping("/account/{accountNumber}/licenses/new")
    public String license(@PathVariable String accountNumber,Model model) {
        Account account = accountRepositories.findAccountByNumber(accountNumber);
        License license=new License(account);
        licenseRepository.save(license);
//        System.out.println(license.getId());
//        licenseRepository.findAllByAccount(account).forEach((a)->System.out.println(a));
        model.addAttribute("account",accountNumber);
        model.addAttribute("licenses",licenseRepository.findAllByAccount(account));
//        System.out.println(account.getLicenses());
        return "licenses";
    }

    @RequestMapping("/account/{accountNumber}/license/{id}/delete")
    public String licenseDel(@PathVariable String accountNumber,Model model,@PathVariable Long id) {
//        System.out.println(accountNumber);
//        System.out.println(id);
        licenseRepository.delete(id);
        return "redirect:/account/"+accountNumber+"/licenses/";
    }

    @RequestMapping("/account/{accountNumber}/delete")
    public String accountDel(@PathVariable String accountNumber,Model model) {
        Account account = accountRepositories.findAccountByNumber(accountNumber);
        accountRepositories.delete(account.getId());
        return "redirect:/accounts/";
    }

    @RequestMapping("/license/{id}/tokens")
//    @ResponseBody
//    public List<Token> tokens(Model model,@PathVariable Long id) {
    public String tokens(Model model,@PathVariable Long id) {
        License license = licenseRepository.findAllById(id);
        model.addAttribute("license",license);
//        System.out.println("License:"+id);
        model.addAttribute("tokens",tokens.findAllByLicense(license));
        return "tokens";
    }

    @RequestMapping("/license/{id}/newtoken")
    public String tokenNew(Model model,@PathVariable Long id) {
        License license = licenseRepository.findAllById(id);
        System.out.println(license);
        Token token=new Token(license);
        System.out.println(token);
        tokens.save(token);
        return "redirect:/license/"+id+"/tokens";
    }

}
