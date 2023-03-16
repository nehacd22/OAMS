package com.kani.oams.service;

import java.util.List;

import com.kani.oams.entity.Category;

public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);

	public void deleteCategory(int id);
	
	public Category getCategory(int id);
	
	public List<Category> showAllCategories();
}
