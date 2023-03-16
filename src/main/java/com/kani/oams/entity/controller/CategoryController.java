package com.kani.oams.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kani.oams.entity.Category;
import com.kani.oams.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/showallcategories")
	public ResponseEntity<List<Category>> showAllCategories() {
		List<Category> allCategories = categoryService.showAllCategories();
		return ResponseEntity.ok(allCategories);
	}

	@PostMapping("/addcategory")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category newCategory = categoryService.addCategory(category);
		return ResponseEntity.ok(newCategory);
	}

	@PutMapping("/updatecategory")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
		return ResponseEntity.ok(categoryService.updateCategory(category));
	}

	@DeleteMapping("/deletecategory/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") int id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.ok("Category deleted");
	}

}
