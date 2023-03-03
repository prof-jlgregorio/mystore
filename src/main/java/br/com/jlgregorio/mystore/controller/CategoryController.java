package br.com.jlgregorio.mystore.controller;
import br.com.jlgregorio.mystore.dto.CategoryDTO;
import br.com.jlgregorio.mystore.exception.CustomExceptionResponse;
import br.com.jlgregorio.mystore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Categories", description = "Here are the endpoints to manage categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Persist a new category in database.", tags = {"Categories"}, responses =
            { @ApiResponse(description = "Success", responseCode = "200", content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CategoryDTO.class)
            )}),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content }),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content }),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = { @Content })
            }
        )
    public CategoryDTO create(@RequestBody CategoryDTO dto){
        return service.create(dto);
    }


    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find a Category by id", tags = {"Categories"}, responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CategoryDTO.class))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content }),
            @ApiResponse(description = "Resource Not Found", responseCode = "404", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema( implementation = CustomExceptionResponse.class))
            }),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = { @Content })
    })
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
