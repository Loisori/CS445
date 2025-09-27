package com.example.demo.Controller.Admin;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.Category;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.pledge;
import com.example.demo.Sercive.CategoryService;
import com.example.demo.Sercive.CommentsService;
import com.example.demo.Sercive.FavouritesService;
import com.example.demo.Sercive.PayoutService;
import com.example.demo.Sercive.PledgeService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.RewardService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"api/Category"})
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final ProjectsService projectsService;

    private final CommentsService commentsService;

    private final RewardService rewardService;

    private final PledgeService pledgeService;

    private final PayoutService payoutService;

    private final FavouritesService favouritesService;

    @PutMapping("update/{id}")
    public ResponseEntity<Apireponsi<Categories>> update(@RequestBody Categories categories, @PathVariable Long id) {
        try {
            Categories categories1 = categoryService.findByID(id);
            if (categories1 == null) {
                Apireponsi<Categories> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không tồn tại", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
         categories1.setName(categories.getName());
            categories1.setDescription(categories.getDescription());
            categories1.setCreatedAt(LocalDateTime.now());
            categoryService.SaveCategory(categories1);
Apireponsi<Categories> repon  =new Apireponsi<>(HttpStatus.OK,"Update Thành công",categories1,null);
return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @PostMapping({"/Create"})
    public ResponseEntity<Apireponsi<Categories>> CreateCategory(@RequestBody Category category) {
        try {
            String chuoi = category.getCategoryName().toUpperCase();
            if (this.categoryService.ExistCategory(chuoi)) {
                Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Loại dự án đã trùng lặp ", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                Categories category1 = new Categories();
                category1.setName(category.getCategoryName().toUpperCase());
                category1.setDescription(category.getDescription());
                category1.setCreatedAt(LocalDateTime.now());
                this.categoryService.SaveCategory(category1);
                Apireponsi<Categories> repon = new Apireponsi(HttpStatus.CREATED, "Create Thành Công", category1, (String) null);
                return new ResponseEntity(repon, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping({"GetAllCategory"})
    public ResponseEntity<Apireponsi<List<Categories>>> GetAllCategory() {
        try {
            List<Categories> categories = this.categoryService.getAllCategories();
            Apireponsi<List<Categories>> repon = new Apireponsi(HttpStatus.OK, "Get All Category", categories, (String) null);
            return new ResponseEntity(repon, HttpStatus.OK);
        } catch (Exception e) {
            Apireponsi<List<Categories>> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @DeleteMapping({"/Delete/{id}"})
    public ResponseEntity<Apireponsi<Categories>> DeleteCategory(@PathVariable Long id) {
        try {
            Categories categories = this.categoryService.getCategory(id);
            if (categories == null) {
                Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tài ", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                this.projectsService.updateIDcategory(id);
                this.categoryService.deleteCategory(id);
                Apireponsi<Categories> repon = new Apireponsi(HttpStatus.OK, "Delete Category Thành Công", categories, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping({"/ListAll"})
    public ResponseEntity<Apireponsi<List<Categories>>> GetAllCategories() {
        try {
            List<Categories> categories = this.categoryService.getAllCategories();
            Apireponsi<List<Categories>> repon = new Apireponsi(HttpStatus.OK, "Get All categories", categories, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Categories>> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping({"/list/{id}"})
    public ResponseEntity<Apireponsi<Categories>> GetReward(@PathVariable Long id) {
        try {
            Categories categories = this.categoryService.getCategory(id);
            if (categories == null) {
                Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                Apireponsi<Categories> repon = new Apireponsi(HttpStatus.OK, "Get categories Thành Công", categories, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception e) {
            Apireponsi<Categories> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
}
