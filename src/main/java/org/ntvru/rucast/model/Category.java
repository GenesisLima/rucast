package org.ntvru.rucast.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name="RU_CATEGORY")
@ToString(exclude = {"show"})
@NoArgsConstructor
@JsonIdentityInfo(
	      generator = ObjectIdGenerators.PropertyGenerator.class, 
	      property = "id")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2869913540540128328L;

	@Id
	@Column(name="CAREGORY_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)	
	@Basic(optional=false)
	private String name;
	
	private String description;

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
//	@ManyToMany(fetch=FetchType.LAZY)		 
//	private List<Show> show;
	
	
	
}
