package com.digt.lice.service;

import javax.servlet.http.HttpServletRequest;

public class SecurityService {
    public boolean checkAuth(HttpServletRequest request) {
        return true;
//        return request.getSession().getAttribute("user") != null;
    }
}
