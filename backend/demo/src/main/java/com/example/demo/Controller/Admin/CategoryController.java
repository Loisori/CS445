package com.example.demo.Controller.Admin;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.Category;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Reward;
import com.example.demo.Sercive.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("auth")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/CreateCategory")
    public ResponseEntity<Apireponsi<Categories>> CreateCategory(@RequestBody Category category) {
        try {
            String chuoi = category.getCategoryName().toUpperCase();

            if (categoryService.ExistCategory(chuoi)){
                Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Loại dự án đã trùng lặp ", null, null);
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Categories category1 = new Categories();
            category1.setName(category.getCategoryName().toUpperCase());
            category1.setDescription(category.getDescription());
            category1.setCreatedAt(LocalDateTime.now());
            categoryService.SaveCategory(category1);
Apireponsi<Categories> repon = new Apireponsi<>(HttpStatus.CREATED,"Create Thành Công",category1,null);
return new ResponseEntity<>(repon, HttpStatus.CREATED);
        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
    @GetMapping("GetAllCategory")
    public ResponseEntity<Apireponsi<List<Categories>>> GetAllCategory() {
        try {
List<Categories> categories = categoryService.getAllCategories();
Apireponsi<List<Categories>> repon = new Apireponsi<>(HttpStatus.OK, "Get All Category", categories, null);
return new ResponseEntity<>(repon, HttpStatus.OK);
         }
        catch (Exception e) {
            Apireponsi<List<Categories>> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Apireponsi<Categories>> DeleteCategory(@PathVariable Long id) {
        try {
            Categories categories = categoryService.getCategory(id);
            if(categories == null){
                Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"Không tồn tài ",null,null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            categoryService.deleteCategory(id);
            Apireponsi<Categories> repon = new Apireponsi<>(HttpStatus.OK,"Delete Category Thành Công",categories,null);
            return  ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
    @GetMapping("/GetAllCategories")
    public ResponseEntity<Apireponsi<List<Categories>>> GetAllCategories() {
        try {
            List<Categories> categories = categoryService.getAllCategories();
            Apireponsi<List<Categories>> repon = new Apireponsi<>(HttpStatus.OK, "Get All categories", categories, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Categories>> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping("/Categories/{id}")
    public ResponseEntity<Apireponsi<Categories>> GetReward(@PathVariable Long id) {
        try {
            Categories categories = categoryService.getCategory(id);
            if (categories == null) {
                Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            Apireponsi<Categories> repon = new Apireponsi<>(HttpStatus.OK, "Get categories Thành Công", categories, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

}
