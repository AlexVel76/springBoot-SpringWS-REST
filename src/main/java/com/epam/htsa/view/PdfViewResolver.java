package com.epam.htsa.view;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class PdfViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        View result = null;
        if (viewName.contains("rest/booking")) {
            result = new PdfTicketReportView();
        }
        return result;
    }

}
