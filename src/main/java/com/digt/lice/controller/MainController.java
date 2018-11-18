package com.digt.lice.controller;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.model.Token;
import com.digt.lice.repositories.AccountRepository;
import com.digt.lice.repositories.LicenseRepository;
import com.digt.lice.repositories.TokenRepository;
import com.digt.lice.service.AccountService;
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
public class MainController {

//    private AccountRepository accountRepositories;
    private AccountService accountService;
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
        model.addAttribute("accounts",accountService.list());
        return "accounts";
    }

    @RequestMapping("/accounts/error")
    public String accountErroe(Model model) {
//        accountRepositories.findAll().forEach(a-> System.out.println(a.getLicenses()));
//        model.addAttribute("accounts",accountRepositories.findAll());
        return "error";
    }

}
