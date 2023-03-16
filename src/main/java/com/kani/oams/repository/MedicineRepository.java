package com.kani.oams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kani.oams.entity.Category;
import com.kani.oams.entity.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Integer>{
	public List<Medicine> findByCategory(Category category);

}
