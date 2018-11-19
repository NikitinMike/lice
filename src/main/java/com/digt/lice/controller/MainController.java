package com.digt.lice.controller;

import com.digt.lice.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class MainController {

    private static final Logger LOG = Logger.getLogger(MainController.class.getName());
    @Autowired
    @Value("${debug}") boolean debug;
    @Autowired
    @Value("${clientId}") String CLIENT_ID;
    @Autowired
    @Value("${license}") String LICENSE;
    @Autowired
    @Value("${trusted}") String TRUSTED;
//    @Autowired SecurityService security;

//    private AccountRepository accountRepositories;
    @Autowired
    AccountService accountService;
//    LicenseRepository licenseRepository;
//    TokenRepository tokens;

    @RequestMapping({"/","/home","/login","/hello"})
    public String welcomeHome(Authentication auth,Model model) {
        System.out.println("HOME:"+auth);
//        if(auth==null) auth=SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(SecurityContextHolder.getContext());
//        model.addAttribute("TRUSTED", TRUSTED);
//        model.addAttribute("LICENSE", LICENSE);
//        model.addAttribute("clientId", CLIENT_ID);
        if (auth!=null) return "redirect:/cabinet";
        return "login";
//        return "redirect:/accounts/";
    }

    @RequestMapping({"/cabinet"})
    public String cabinet(Authentication auth,Model model) {
        System.out.println("CABINET:"+auth);
//        System.out.println(SecurityContextHolder.getContext());
//        model.addAttribute("TRUSTED", TRUSTED);
//        model.addAttribute("LICENSE", LICENSE);
//        model.addAttribute("clientId", CLIENT_ID);
        if (auth!=null) return "redirect:/accounts/";
        if(auth==null) auth=SecurityContextHolder.getContext().getAuthentication();
        return "index";
    }

    @RequestMapping("/accounts")
    public String account(Authentication auth, Model model) {
        System.out.println("ACCOUNTS:"+auth);
        System.out.println(SecurityContextHolder.getContext());
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
