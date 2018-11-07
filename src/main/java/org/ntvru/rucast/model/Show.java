package org.ntvru.rucast.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.PersistenceConstructor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@ToString(exclude = {"categories","episodes"})
@NoArgsConstructor
@Table(name="RU_SHOW")
@NamedNativeQueries({@NamedNativeQuery(name="Show.findAllShowNames", query="select s.name from ru_show s;")})
@JsonIdentityInfo(
	      generator = ObjectIdGenerators.PropertyGenerator.class, 
	      property = "id")
public class Show implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757906485161215774L;

	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String name;
	private String description;
	
	@PersistenceConstructor
	public Show(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinTable(name="ru_product_category")
	@JsonIgnore
	private List<Category> categories;
	
	
	@OneToMany(mappedBy="show", fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Episode> episodes;

	
	
	
	
	
}
