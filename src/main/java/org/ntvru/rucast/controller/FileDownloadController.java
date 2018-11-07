package org.ntvru.rucast.controller;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.ntvru.rucast.model.FileDocument;
import org.ntvru.rucast.model.Show;
import org.ntvru.rucast.service.FileService;
import org.ntvru.rucast.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Genesis Lima on 26/10/2016.
 */
@RestController
@RequestMapping("download")
public class FileDownloadController {

    private static final Logger LOGGER = Logger.getLogger(FileDownloadController.class);

    @Autowired
    private FileService service;
    
    
    
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/list")
    public ModelAndView getFiles(ModelMap model) throws UnknownHostException {
            
        model.addAttribute("fileDocuments", service.findAll());
        for(FileDocument fileDocument: service.findAll()){
        	System.out.println("SHOW: "+fileDocument.getEpisode().getId()+"- "+fileDocument.getFileName());
        }
     //System.out.println("FILES: "+service.findAll().get(1).getFileName());
        return new ModelAndView("download", model);
    }

    @RequestMapping(value = "/file/{id}/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> load(@PathVariable("id") Long id, @PathVariable("fileName") String fileName) throws UnknownHostException {

        BufferedInputStream inputStream = null;
          System.out.println("SHOW ID: "+id);
        HttpHeaders headers = new HttpHeaders();
           /*Added from Mongo*/
        Optional<FileDocument> fileDocument= service.findById(id);
        System.out.println(fileDocument);
        String dir = fileDocument.get().getFilePath();
        String showId = String.valueOf(fileDocument.get().getEpisode().getId());
        System.out.println("NAME OF FILE DOCUMENT: "+dir);
        String topic = fileDocument.get().getEpisode().getTopic();
        System.out.println("NAME OF FILE DOCUMENT: "+topic);
        String type = fileDocument.get().getFileType();

        try {

            Path path = Paths.get(dir + File.separator + topic);

            String temp = path.getFileName().toString();
             System.out.println("LIST "+"http://"+request.getLocalAddr()+":"+request.getLocalPort()+""+request.getContextPath()+"/downloads/files/"+topic);
//             String ipAddress = request.getHeader("X-FORWARDED-FOR");  
//  		   if (ipAddress == null) {  
//  			   ipAddress = request.getRemoteAddr(); 
//  		   }
  			   
  			   //System.out.println("Client IP: "+ipAddress+" Date: "+ new GregorianCalendar());
            if (temp.equalsIgnoreCase(topic)) {

                inputStream = new BufferedInputStream(new FileInputStream(path.toFile()));

                headers.setContentType(MediaType.valueOf(type));

                return new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), headers, HttpStatus.OK);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return null;
    }
}
