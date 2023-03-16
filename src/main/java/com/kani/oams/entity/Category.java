package com.kani.oams.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	/*
	 * Adding JsonIgnore so that JSON can ignore 'medicines' list while
	 * serializing Category object in ResponseBody of add/update medicine. This
	 * avoids StackOverflowError which occurs due to the cyclic dependency between
	 * Category & Medicine objects.
	 */
	//@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Medicine> medicines;

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public Category() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
