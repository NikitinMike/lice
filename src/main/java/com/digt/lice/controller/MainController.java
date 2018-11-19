package com.digt.lice.controller;

import com.digt.lice.repositories.LicenseRepository;
import com.digt.lice.repositories.TokenRepository;
import com.digt.lice.service.AccountService;
import com.digt.lice.service.SecurityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@AllArgsConstructor
@NoArgsConstructor
//@RequestMapping(value = "/license")
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());
//    @Autowired
    @Value("${debug}") boolean debug;
//    @Autowired
    @Value("${clientId}") String CLIENT_ID;
//    @Autowired
    @Value("${license}") String LICENSE;
//    @Autowired
    @Value("${trusted}") String TRUSTED;
//    @Autowired
    SecurityService security;

//    private AccountRepository accountRepositories;
    @Autowired
    AccountService accountService;
//    LicenseRepository licenseRepository;
//    TokenRepository tokens;

    @RequestMapping({"/","home"})
    public String welcome(Authentication auth, Model model) {
        System.out.println("HOME:"+auth);
        model.addAttribute("TRUSTED", TRUSTED);
        model.addAttribute("LICENSE", LICENSE);
        model.addAttribute("clientId", CLIENT_ID);
        if (auth!=null) return "redirect:cabinet";
        return "index";
//        return "redirect:/accounts/";
    }

    @RequestMapping({"cabinet"})
    public String cabinet(Authentication auth, Model model) {
        System.out.println("CABINET:"+auth);
        model.addAttribute("TRUSTED", TRUSTED);
        model.addAttribute("LICENSE", LICENSE);
        model.addAttribute("clientId", CLIENT_ID);
        if (auth!=null) return "redirect:accounts";
        return "cabinet";
    }

    @RequestMapping({"/authorize","/login"})
    public String login(Authentication auth, Model model) {
        System.out.println("LOGIN");
        return "login";
    }

    @RequestMapping("accounts")
    public String account(Authentication auth, Model model) {
        System.out.println("ACCOUNTS:"+auth);
//        accountRepositories.findAll().forEach(a-> System.out.println(a.getLicenses()));
        model.addAttribute("accounts",accountService.list());
        return "accounts";
    }

    @RequestMapping("accounts/error")
    public String accountErroe(Model model) {
//        accountRepositories.findAll().forEach(a-> System.out.println(a.getLicenses()));
//        model.addAttribute("accounts",accountRepositories.findAll());
        return "error";
    }

}
