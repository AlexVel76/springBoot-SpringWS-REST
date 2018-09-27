package com.epam.htsa.controller;

import com.epam.htsa.facade.TicketFacade;
import com.epam.htsa.service.EventService;
import com.epam.htsa.service.UserService;
import com.epam.htsa.service.impl.JAXBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import java.io.IOException;


@Controller()
@RequestMapping(value = "/export")
public class ExportController {

    @Autowired
    private TicketFacade ticketFacade;

    @Resource(name = "pdfTicketReportView")
    private View pdfTicketReportView;

    @Autowired
    private JAXBService jaxbService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/tickets/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ModelAndView downloadAllTickets() {
        ModelAndView mv = new ModelAndView(pdfTicketReportView, "tickets",
                ticketFacade.getAllTickets());
        return mv;
    }

    @GetMapping(value = "/users/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public byte[] genereteUsersXml() throws IOException {
        return jaxbService.convertFromEntityToXML(userService.getAll(), "users.xml");
    }

    @GetMapping(value = "/events/xml", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public byte[] generateEventsXml() throws IOException {
        return jaxbService.convertFromEntityToXML(eventService.getAll(), "events.xml");
    }
}
