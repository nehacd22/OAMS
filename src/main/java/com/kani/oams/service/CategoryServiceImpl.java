package com.kani.oams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kani.oams.entity.Category;
import com.kani.oams.exceptions.CategoryNotFoundException;
import com.kani.oams.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	static final String CATEGORY_NOT_FOUND = "category-not-found";

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MessageSource msgSource;

	@Override
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		doesCategoryExist(category.getId());
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategory(int id) {
		doesCategoryExist(id);
		/* TODO Validate if this category has medicines associated with it. 
			If yes, then throw a new user defined exception and don't allow delete */
		categoryRepository.deleteById(id);
	}

	@Override
	public Category getCategory(int id) {
		Optional<Category> optionalCategory = categoryRepository.findById(id);
		return optionalCategory.get();
	}

	@Override
	public List<Category> showAllCategories() {
		return categoryRepository.findAll();
	}

	/**
	 * Method to check if category exists in db for given category id. 
	 * If not it throws exception.
	 * 
	 * @param categoryId
	 */
	private void doesCategoryExist(int categoryId) {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		optionalCategory.orElseThrow(() -> new CategoryNotFoundException(
				msgSource.getMessage(CATEGORY_NOT_FOUND, new String[] { String.valueOf(categoryId) }, null)));
	}
}
