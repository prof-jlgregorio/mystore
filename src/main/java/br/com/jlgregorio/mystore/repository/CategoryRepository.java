package br.com.jlgregorio.mystore.repository;

import br.com.jlgregorio.mystore.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //..annotation do define it as a Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

    //..return a list of CategoryModel ordered by name
    public List<CategoryModel> findByNameContainsIgnoreCaseOrderByName(String name);

}
