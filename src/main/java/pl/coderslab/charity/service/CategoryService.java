package pl.coderslab.charity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getActiveCategories() {
        return categoryRepository.findAll().stream()
                .filter(Category::isActive)
                .collect(Collectors.toList());
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void disableCategory(Long id) {
        Category category = getCategory(id);
        category.setActive(false);
        categoryRepository.save(category);
    }

    public void enableCategory(Long id) {
        Category category = getCategory(id);
        category.setActive(true);
        categoryRepository.save(category);
    }
}
