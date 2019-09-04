package org.ntvru.rucast.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.PersistenceConstructor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="RU_EPISODE")
@Data
@ToString(exclude = "fileDocument")
@JsonIdentityInfo(
	      generator = ObjectIdGenerators.PropertyGenerator.class, 
	      property = "id")
public class Episode implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4631920787754669413L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EPISODE_ID")
	private Long id;
	
	private String topic;
	private String synopsis;	
	private LocalDate saveDate = LocalDate.now();
	private String duration;
	
	@ManyToOne
	@JoinColumn(name="fk_episode_show")
	private Show show;
	
	
	
	
	
	
	
	
	public Episode() {
		super();
	}
	
	@OneToOne(cascade= {CascadeType.ALL}, mappedBy="episode", fetch=FetchType.LAZY)
	FileDocument fileDocument;


    @PersistenceConstructor
	public Episode(String topic, String synopsis
			) {
		super();
		
		this.synopsis = synopsis;		
		
		
	}


	



    	
    
}
