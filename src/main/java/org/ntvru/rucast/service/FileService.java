package org.ntvru.rucast.service;

import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ntvru.rucast.model.FileDocument;
import org.ntvru.rucast.repository.FileDocumentRepository;
import org.ntvru.rucast.utils.OSValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *  Created by Genesis Lima
 */
@Service
public class FileService {

    @Autowired
    private FileDocumentRepository fileDocumentRepository;
    
   
    
  
  
  
       

    public void save(FileDocument fileDocument) throws UnknownHostException {
    	//System.out.println("PATH:"+path+name);  
    	        // showService.save(show.getName(), show.getTopic(), show.getSynopsis());
    	
    	        System.out.println("File Document:"+fileDocument.getFileName());    	
    	    	//System.out.println("File Document id:"+fileDocument.getShow().getId());
    	        
    	    	
    	    	fileDocumentRepository.save(fileDocument);
    	
    }

    public List<FileDocument> findAll() {
    	List<FileDocument> fileDocuments = new ArrayList<>();
    	
    	fileDocumentRepository.findAll().forEach(fileDocuments::add);

        return fileDocuments;
    }

    public Optional<FileDocument> findByShowId(Long id) {

        return fileDocumentRepository.findById(id);
    }

    public void delete(FileDocument fileDocument) {

    	fileDocumentRepository.delete(fileDocument);
    }

    public Optional<FileDocument> findById(Long id) {
    	
    	return fileDocumentRepository.findById(id);
    }
    public String createPath() {

        LocalDate localDate = LocalDate.now();
        String dd = String.valueOf(localDate.getDayOfMonth());
        String mm = String.valueOf(localDate.getMonthValue());
        String yyyy = String.valueOf(localDate.getYear());

       // return "." + "/" + yyyy + "/" + mm+ "/" +dd;
       // return "c:\\uploads\\"+yyyy+"\\"+mm;
        return (OSValidator.isWindows()?yyyy+"\\"+mm:"/" + yyyy + "/" + mm+ "/" +dd);
      
    }
    
   
    
    
    
    
}
