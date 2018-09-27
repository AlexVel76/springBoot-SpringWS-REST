package com.epam.htsa.controller;

import com.epam.htsa.entity.Event;
import com.epam.htsa.entity.User;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.facade.EventFacade;
import com.epam.htsa.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.epam.htsa.service.impl.JAXBService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ImportController {

	@Autowired
	private JAXBService xMLConverter;

	@Autowired
	private UserFacade userFacade;

	@Autowired
	private EventFacade eventFacade;

	@PostMapping(value = "/uploadFiles")
	public void uploadXml(final @RequestParam("name") String[] names, final @RequestParam("file") MultipartFile[] files)
			throws IOException {

		if (names.length != files.length)
			throw new HomeTaskExceptioin("Fill mandatory fields");

		try {
			User user = (User) xMLConverter.convertFromXMLToEntity(files[0].getInputStream(), User.class);
			userFacade.save(user);
			Event event = (Event) xMLConverter.convertFromXMLToEntity(files[1].getInputStream(), Event.class);
			eventFacade.save(event);
		} catch (Exception e) {
			throw new HomeTaskExceptioin("Error while Uploading :" + e.getMessage());
		}
	}

}
