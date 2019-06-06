package com.excilys.cdb.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {
 
    @GetMapping("/errors")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
         
        ModelAndView errorPage = new ModelAndView("errorPage");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
 
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request :(";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized :(";
                break;
            }
            case 404: {
                errorMsg = " Error 404: Page not found. Too bad bitch!";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error :'(";
                break;
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
    
    @GetMapping("/500Error")
    public void throwRuntimeException() {
        throw new NullPointerException("Throwing a null pointer exception");
    }
    
    @GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
}