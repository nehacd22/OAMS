package com.kani.oams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.kani.oams.entity.Category;
import com.kani.oams.entity.Medicine;
import com.kani.oams.exceptions.MedicineNotFoundException;
import com.kani.oams.repository.MedicineRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	static final String MEDICINE_NOT_FOUND = "medicine-not-found";

	@Autowired
	MedicineRepository medicineRepo;
	@Autowired
	CategoryService categoryService;
	@Autowired
	private MessageSource msgSource;

	@Override
	public Medicine addMedicine(Medicine medicine) {
		return medicineRepo.save(medicine);
	}

	@Override
	public Medicine updateMedicine(Medicine medicine) {
		doesMedicineExist(medicine.getId());
		return medicineRepo.save(medicine);
	}

	@Override
	public Medicine viewMedicine(int id) {
		return medicineRepo.findById(id).get();
	}

	@Override
	public void deleteMedicine(int id) {
		doesMedicineExist(id);
		medicineRepo.deleteById(id);
	}

	@Override
	public List<Medicine> viewAllMedicines() {
		return medicineRepo.findAll();
	}

	@Override
	public List<Medicine> viewMedicinesByCategory(int categoryId) {
		Category category = categoryService.getCategory(categoryId);
		List<Medicine> medicinesList = medicineRepo.findByCategory(category);
		return medicinesList;
	}

	/**
	 * Method to check whether medicine exists in db for medicine id. Else it throws
	 * MedicineNotFoundExcpetion
	 * 
	 * @param medicineId
	 */
	private void doesMedicineExist(int medicineId) {
		Optional<Medicine> optMedicine = medicineRepo.findById(medicineId);
		optMedicine.orElseThrow(() -> new MedicineNotFoundException(
				msgSource.getMessage(MEDICINE_NOT_FOUND, new String[] { String.valueOf(medicineId) }, null)));
	}

}
