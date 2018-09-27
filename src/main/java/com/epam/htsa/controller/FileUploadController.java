package com.epam.htsa.controller;

import java.io.IOException;
import java.util.stream.Collectors;

import com.epam.htsa.entity.User;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.facade.EventFacade;
import com.epam.htsa.facade.UserFacade;
import com.epam.htsa.service.impl.JAXBService;
import com.epam.htsa.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    private JAXBService xMLConverter;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private EventFacade eventFacade;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping()
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "upload";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping()
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "Successfully uploaded " + file.getOriginalFilename() + "!");
        try {
            User user = (User) xMLConverter.convertFromXMLToEntity(file.getInputStream(), User.class);
            userFacade.save(user);
        } catch (Exception e) {
            throw new HomeTaskExceptioin("Error while Uploading :" + e.getMessage());
        }

        return "redirect:/fileUpload";
    }
}
