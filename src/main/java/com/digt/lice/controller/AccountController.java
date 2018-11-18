package com.digt.lice.controller;


import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.service.AccountService;
import com.digt.lice.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.thymeleaf.util.StringUtils.randomAlphanumeric;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/account")
public class AccountController {

    private AccountService accountService;
    private LicenseService licenseService;

    @RequestMapping("/new")
//    @ResponseBody
    public String create() {
        Account account = accountService.create("Misha "+randomAlphanumeric(8));
        return "redirect:/account/licenses/"+account.getNumber();
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Account> listAll() {
        return accountService.list();
    }

    @RequestMapping("/get/{accountNumber}")
    @ResponseBody
    public void get(@PathVariable String accountNumber) {
        accountService.get(accountNumber);
    }

    @RequestMapping("/del/{accountNumber}")
//    @ResponseBody
    public String delete(@PathVariable String accountNumber) {
        accountService.delete(accountNumber);
        return "redirect:/accounts/";
    }

    @RequestMapping("/newlicense/{accountNumber}")
    public String license(@PathVariable String accountNumber,Model model) {
        Account account = accountService.get(accountNumber);
        model.addAttribute("account",accountNumber);
        License license = accountService.addLicense(account);
//        model.addAttribute("licenses",licenseService.list(account));
//        return "licenses";
//        model.addAttribute("tokens",licenseService.list(account));
//        return "tokens";
        return "redirect:/license/"+license.getId()+"/tokens";
    }

    @RequestMapping("/licenses/{accountNumber}")
    public String licenseView(@PathVariable String accountNumber,Model model) {
//        System.out.println(accountNumber);
        Account account = accountService.get(accountNumber);
//        System.out.println(account);
        model.addAttribute("account",accountNumber);
        model.addAttribute("licenses",licenseService.list(account));
        return "licenses";
    }

}
