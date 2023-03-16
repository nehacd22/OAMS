package com.kani.oams.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kani.oams.entity.Medicine;
import com.kani.oams.service.MedicineService;

@RequestMapping("/medicine")
@RestController
public class MedicineController {

	@Autowired
	private MedicineService medicineService;

	@PostMapping("/addmedicine")
	public ResponseEntity<Medicine> addMedicine(@RequestBody Medicine medicine) {
		Medicine newMedicine = medicineService.addMedicine(medicine);
		return ResponseEntity.ok(newMedicine);
	}

	@PutMapping("/updatemedicine")
	public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine) {
		Medicine updatedMedicine = medicineService.updateMedicine(medicine);
		return ResponseEntity.ok(updatedMedicine);
	}

	@DeleteMapping("/deletemedicine/{medicineId}")
	public ResponseEntity<String> deleteMedicine(@PathVariable("medicineId") int id) {
		medicineService.deleteMedicine(id);
		return ResponseEntity.ok("Medicine deleted");
	}

	@GetMapping("/viewallmedicines")
	public ResponseEntity<List<Medicine>> viewAllMedicines() {
		return ResponseEntity.ok(medicineService.viewAllMedicines());
	}

	@GetMapping("/viewmedicinesbycategory/{categoryId}")
	public ResponseEntity<List<Medicine>> viewMedicinesByCategory(@PathVariable("categoryId") int categoryId) {
		return ResponseEntity.ok(medicineService.viewMedicinesByCategory(categoryId));
	}
}
