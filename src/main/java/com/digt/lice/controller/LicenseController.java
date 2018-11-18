package com.digt.lice.controller;

import com.digt.lice.model.Account;
import com.digt.lice.model.License;
import com.digt.lice.service.LicenseService;
import com.digt.lice.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/license")
public class LicenseController {

    private LicenseService licenseService;
    private TokenService tokenService;

    @RequestMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        License license = licenseService.get(id);
        Account account=license.getAccount();
        licenseService.delete(id);
        return "redirect:/account/licenses/"+account.getNumber();
    }

    @RequestMapping("/{id}/tokens")
//    @ResponseBody public List<Token> tokens(Model model,@PathVariable Long id) {
    public String tokens(Model model,@PathVariable Long id) {
        License license = licenseService.get(id);
        model.addAttribute("license",license);
        model.addAttribute("tokens",license.getTokens());
        return "tokens";
    }

    @RequestMapping("/{id}/newtoken")
    public String tokenNew(Model model,@PathVariable Long id) {
        License license=licenseService.get(id);
        if(license.allowIsuueTokens()) tokenService.create(license);
        return "redirect:/license/"+id+"/tokens";
    }

    @RequestMapping("/{id}/token/{tokenId}/delete")
    public String tokenDel(@PathVariable Long id,@PathVariable Long tokenId) {
        tokenService.delete(tokenId);
        return "redirect:/license/"+id+"/tokens";
    }

}
