package org.ntvru.rucast.model;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;



/**
 * Created by Genesis Lima
 */
@Entity
@Table(name="RU_FILE_DOCUMENT")
@Data
@ToString(exclude = "episode")
public class FileDocument implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1259176997906412295L;

	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="FILE_DOCUMENT_ID")	    
   	 private Long id;
	

  
    private String filePath;
   
    private String fileName;

    private String fileSize;

    private String fileType;
    
   private String uri;
   
//   @ManyToOne(cascade=CascadeType.ALL)
//   @JoinColumn(name="show_id")
//   private Show show; 
    
   
    
    @OneToOne(fetch=FetchType.LAZY) 
    @MapsId  
    private Episode episode;
    

    public FileDocument() {
        super();
    }
    
   
 

    public FileDocument(String filePath, String fileName, String fileSize, String fileType,String uri) {
    	
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.uri = uri;
      //  this.show = show;
    }




	
	


}




    
    

