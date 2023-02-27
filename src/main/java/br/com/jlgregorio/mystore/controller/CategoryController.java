package br.com.jlgregorio.mystore.controller;
import br.com.jlgregorio.mystore.dto.CategoryDTO;
import br.com.jlgregorio.mystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO dto){
        return service.create(dto);
    }
    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @PutMapping
    public CategoryDTO update(@RequestBody CategoryDTO dto){
        return service.update(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id){
        CategoryDTO dto = service.findById(id);
        service.delete(dto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<CategoryDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/search")
    public List<CategoryDTO> findByName(@RequestParam(name = "name", defaultValue = "") String name){
        return service.findByName(name);
    }

}
