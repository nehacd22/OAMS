package com.kani.oams.service;

import java.util.List;

import com.kani.oams.entity.Medicine;

public interface MedicineService {
	
	public Medicine addMedicine(Medicine medicine);

	public Medicine updateMedicine(Medicine medicine);

	public Medicine viewMedicine(int id);

	public void deleteMedicine(int id);

	public List<Medicine> viewAllMedicines();

	public List<Medicine> viewMedicinesByCategory(int categoryId);

}
