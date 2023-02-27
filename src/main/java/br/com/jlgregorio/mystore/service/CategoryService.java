package br.com.jlgregorio.mystore.service;
import br.com.jlgregorio.mystore.dto.CategoryDTO;
import br.com.jlgregorio.mystore.exception.ResourceNotFoundException;
import br.com.jlgregorio.mystore.mapper.CustomModelMapper;
import br.com.jlgregorio.mystore.model.CategoryModel;
import br.com.jlgregorio.mystore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    //dependency injection
    @Autowired
    private CategoryRepository repository;

    //save a model
    public CategoryDTO create(CategoryDTO dto){
        //converts DTO object to model object
        CategoryModel model = CustomModelMapper.parseObject(dto, CategoryModel.class);
        //..persists the model and return a DTO object
        return CustomModelMapper.parseObject(repository.save(model), CategoryDTO.class);
    }

    //find a model using the id
    public CategoryDTO findById(int id){
        //retrive the model using the id
       CategoryModel model = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(null));
       //..converts model in DTO object
       return CustomModelMapper.parseObject(model, CategoryDTO.class);
    }

    public CategoryDTO update(CategoryDTO dto){
        //verify if object exists
        CategoryModel model = repository.findById(dto.getId()).orElseThrow(()-> new ResourceNotFoundException(null));
        //converts dto to model
        model = CustomModelMapper.parseObject(dto, CategoryModel.class);
        //persits the model and return a dto
        return CustomModelMapper.parseObject(repository.save(model), CategoryDTO.class);
    }

    public void delete(CategoryDTO dto){
        //verify if object exists
        CategoryModel model = repository.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException(null));
        //..delete the object
        repository.delete(model);
    }

    public List<CategoryDTO> findAll(){
        List<CategoryModel> list = repository.findAll();
        return CustomModelMapper.parseObjectList(list, CategoryDTO.class);
    }

    public List<CategoryDTO> findByName(String name){
        List<CategoryModel> list = repository.findByNameContainsIgnoreCaseOrderByName(name);
        return CustomModelMapper.parseObjectList(list, CategoryDTO.class);
    }



}
