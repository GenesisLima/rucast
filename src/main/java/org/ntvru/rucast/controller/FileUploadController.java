package org.ntvru.rucast.controller;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.ntvru.rucast.model.Episode;
import org.ntvru.rucast.model.FileDocument;
import org.ntvru.rucast.service.CategoryService;
import org.ntvru.rucast.service.EpisodeService;
import org.ntvru.rucast.service.FileService;
import org.ntvru.rucast.service.ShowService;
import org.ntvru.rucast.utils.OSValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;



@Controller
@RequestMapping("/upload")
public class FileUploadController {

    private static final Logger LOGGER = Logger.getLogger(FileUploadController.class);

     
   @Autowired
   private FileService fileService;
   
   @Autowired
   private EpisodeService episodeService;
   
   @Autowired
   private ShowService showService;
   
   @Autowired
   private CategoryService categoryService;
   
   
   @Autowired
   private HttpServletRequest request;
   
   
   
  

    private String path;
    private String name;
    private String type;
    private String size;
    public static final String ROOT = "upload-dir";
    Tika tika = new Tika();
	
   

    @RequestMapping(value = "/file", method = RequestMethod.POST)
  //  @RequestMapping(value = "/episode", method = RequestMethod.POST)
    @Secured(value={"ADMIN"})
    public String uploadFileHandler(@RequestParam("file") MultipartFile file, @RequestParam("showName") String showName, @RequestParam("categories") String[] categories, ModelMap model, RedirectAttributes redirectAttributes, FileDocument fileDocument, Episode episode) throws UnknownHostException {
    	
    	   System.out.println("Show Name "+showName);
   System.out.println("Show "+episode.toString()+"Tema: "+episode.getTopic());
   System.out.println("Show "+episode.toString()+"Sinopse: "+episode.getSynopsis());
   System.out.println("Show "+episode.toString()+"Data: "+episode.getSaveDate());

  // ModelAndView modelAndView = new ModelAndView("redirect:/");
   
    
   
   
        path = "";
        name = file.getOriginalFilename();
        type = file.getContentType();
        size = Long.toString(file.getSize());
        
        
        String newFileMessage = "O upload do arquivo " + name + " foi realizado com sucesso!"; 
        String alreadyExistsMessage =  "O arquivo " + name + " já foi salvo anteriormente!"; 
      
        String uri = "http://"+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath()+"/downloads/files/";
        
       
        try {
            byte[] bytes = file.getBytes();

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
            
          
                
            File directory = new File(dir.toString());

            

            // Create the file on server
            //Windows
           // File serverFile = new File(dir.getAbsolutePath() + "\\" + name);
            //Linux
        
            if (!directory.exists()) {
            	directory.mkdirs();
            }
             File audioFile = new File(directory.getAbsolutePath()+getSlashByOS()+name);
            if (!audioFile.exists()) {
            	
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(audioFile));

                stream.write(bytes);

                
                //System.out.println("EPISODE"+episode);

                
               
                             
              //  episode.setShow(showService.findShowByName(showName).get());
                //System.out.println("EPISODE SHOW "+episode.getShow() );
                for(int i=0; i < categories.length;i++) {
               episode.getShow().getCategories().add(categoryService.findCategoryByName(categories[i]).get());
//                System.out.println("CATEGORY BY NAME "+categoryService.findCategoryByName(categories[i]).get());   
                }
                
                Map<String,String> audioMetaData = extractMetadata(audioFile);
                String value = audioMetaData.get("xmpDM:duration");
                System.out.println("VALUE "+value);
               // System.out.println("TIME "+TimeUnit.MILLISECONDS.toSeconds(Long.valueOf(value)));
                episode.setDuration(value);
//                System.out.println("DURATION "+value);
//                System.out.println("DURATION II - The Mission"+audioMetaData.keySet());
                episodeService.save(episode);
                
                fileDocument.setEpisode(episode);             
                
                fileDocument.setFilePath(path);
                fileDocument.setFileName(name);
                fileDocument.setFileSize(size);
                fileDocument.setFileType(type);                
                fileDocument.setUri(uri);   
                fileDocument.setEpisode(episode);
                
                fileService.save(fileDocument);
//                System.out.println("TIKA "+tika.detect(bytes));
//                System.out.println("TIKA PARSE "+extractMetadata(audioFile));
//
//                System.out.println("FILE DOCUMENT "+fileDocument);

             

                System.out.println(newFileMessage);
                                        
     
                System.out.println("EPISODE "+episode);
          
                stream.close();
                redirectAttributes.addFlashAttribute("message", newFileMessage);
            } else {

            	 redirectAttributes.addFlashAttribute("message", alreadyExistsMessage);
            }

        } catch (Exception e) {
            LOGGER.error("O upload do arquivo " + name + " falhou devido => " + e.getMessage());
        }
        
        

        return "redirect:/";
    }

    
  private String getSlashByOS() {
    	
    	return (OSValidator.isWindows()?"\\":"/");
    }
    
    /**
     * Upload multiple file using Spring Controller
     */
//    @RequestMapping(value = "/multipleFiles", method = RequestMethod.POST)
//    public ModelAndView uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files, ModelMap model) {
//
//        String mensagem = "";
//
//        for (MultipartFile file : files) {
//
//            path = "";
//            name = file.getOriginalFilename();
//            type = file.getContentType();
//            size = file.getSize();
//
//            try {
//                byte[] bytes = file.getBytes();
//
//                // Creating the directory to store file
//             //   path = service.createPath();
//
//                File dir = new File(path);
//
//                if (!dir.exists()) {
//                    dir.mkdirs();
//                }
//
//                // Create the file on server
//                File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
//
//                if (!serverFile.exists()) {
//
//                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//
//                    stream.write(bytes);
//
//                    stream.close();
//
//                  //  service.save(path, name, size, type);
//
//                    mensagem = mensagem + "O upload do arquivo " + name + " foi realizado com sucesso! <br />";
//                } else {
//
//                    mensagem = mensagem + "N�o foi salvo! O arquivo " + name + " ja existe.";
//                }
//            } catch (Exception e) {
//                LOGGER.error("O upload do arquivo " + name + " falhou devido => " + e.getMessage());
//            }
//        }
//
//        model.addAttribute("mensagem", mensagem);
//
//        return new ModelAndView("/uploadMultiple", model);
//    }
    
    
    private void addCategoriesToEpisode(String[] categories, Episode episode) {
    	
        for(int i=0; i < categories.length;i++) {
        	//gson.fromJson(categories[categories.length], Category.class);
        	System.out.println("CATEGORY "+categories[i]);
        
        	// episode.getShow().getCategories().add(category);
        	 }
    	
    }
    
    private Map<String,String> extractMetadata(File file) throws IOException, SAXException, TikaException {
    	Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();
        
        parser.parse(inputstream, handler, metadata, context);
        System.out.println(handler.toString());

        
        String[] metadataNames = metadata.names();
        Map<String,String> audioFileMetaData = new HashMap<String,String>();
        for(String name : metadataNames) {
        	audioFileMetaData.put(name, metadata.get(name));
           System.out.println(name + ": " + metadata.get(name));
        }
		return audioFileMetaData;
    }
    
    
}
