package org.ntvru.rucast.controller;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ntvru.rucast.model.FileDocument;
import org.ntvru.rucast.service.EpisodeService;
import org.ntvru.rucast.service.FileService;
import org.ntvru.rucast.streaming.MultipartFileSender;
import org.ntvru.rucast.utils.OSValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * Created by Genesis Lima
 */
@RestController
//@RequestMapping("/api/podcast/{idPodcast}/items")
@RequestMapping("downloads")
public class ItemController {

protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
//    @Resource private ItemBusiness itemBusiness;
//
//    @Autowired
//    protected ItemDownloadManager itemDownloadManager;

       @Autowired
       EpisodeService service;
       
       @Autowired
    	private FileService fileService;
       
       private String path;
       
       

//    @RequestMapping(value="/files/{id}", method = RequestMethod.GET)
//    public void getEpisodeFile(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        logger.debug("Download of item file {}", id);
//       Optional<FileDocument> fileDocument= fileService.findById(id);
//       
//        
//      //  if (show.isDownloaded()) {
//            logger.debug("R�cup�ration en local de l'item {} au chemin {}", id, fileDocument.get().getUri());
//            
//            
//            
//            // Creating the directory to store file
//            FileSystem fs = FileSystems.getDefault();
//            
//            //ApplicationContextAware contextProvider = new ApplicationContextProvider();
//            Path dir = null;
//            
//            path = fileService.createPath();
//            
//            
//            if(OSValidator.isWindows()) {
//            	 dir = fs.getPath("C:", "audiocast", path);	
//            	
//            }else if(OSValidator.isUnix()){
//            	dir = fs.getPath("/", "opt","audiocast", path);	
//            	
//            }
//            
//            System.out.println("SHOW ON ITEM CONTROLLER :"+dir+getSlashByOS()+fileDocument.get().getFileName());
//            
//            MultipartFileSender.fromURIString(dir+getSlashByOS()+fileDocument.get().getFileName())
//                    .with(request)
//                    .with(response)
//                .serveResource();
//
//       // } else {
//         //   response.sendRedirect(show.getUrl());
//       // }
//
//    }
  
   @RequestMapping(value="/files/{name}", method = {RequestMethod.GET})
   public void getEpisodeByFileName(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws Exception {
       logger.debug("Download of item file {}", name);
      FileDocument fileDocument= fileService.findByName(name);
      
       
     //  if (show.isDownloaded()) {
          logger.debug("R�cup�ration en local de l'item {} au chemin {}", name, fileDocument.getUri());
           
           
           
           // Creating the directory to store file
           FileSystem fs = FileSystems.getDefault();
           
           //ApplicationContextAware contextProvider = new ApplicationContextProvider();
           Path dir = null;
           
           path = fileService.createPath();
           
           
           if(OSValidator.isWindows()) {
           	 dir = fs.getPath("C:", "audiocast", path);	
           	
           }else if(OSValidator.isUnix()){
           	dir = fs.getPath("/", "opt","audiocast", path);	
           	
           }
           
           System.out.println("SHOW ON ITEM CONTROLLER :"+dir+getSlashByOS()+fileDocument.getFileName());
           
           MultipartFileSender.fromURIString(dir+getSlashByOS()+fileDocument.getFileName())
                   .with(request)
                   .with(response)
               .serveResource();

      // } else {
        //   response.sendRedirect(show.getUrl());
      // }
   }
   
  private String getSlashByOS() {
   	
   	return (OSValidator.isWindows()?"\\":"/");
   }
    
}
