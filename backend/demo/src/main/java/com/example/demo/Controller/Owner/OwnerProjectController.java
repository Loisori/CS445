package com.example.demo.Controller.Owner;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.Keyword;
import com.example.demo.DTO.Project;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Role;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Sercive.AuthService;
import com.example.demo.Sercive.CategoryService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/creator")
public class OwnerProjectController {
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService roleservice;
    @Autowired
    private AuthService authService;

 private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final String UPLOAD_DIR = "uploads/projects/";


    @PostMapping("/CreateProjects")
    public ResponseEntity<Apireponsi<Projects>> CreateProject(@ModelAttribute Project project,
                                                              @RequestParam(value = "image", required = false) MultipartFile image
    ) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

           Users user = userService.getUserByUsername(username);



            Projects projects = new Projects();
            projects.setTitle(project.getTitle());
            projects.setDescription(project.getDescription());
            projects.setGoalAmount(project.getGoalAmount());
            projects.setPledgedAmount(project.getPledgedAmount());   // gán trực tiếp
            projects.setCollectedAmount(project.getCollectedAmount()); // gán trực tiế
            projects.setStartDate(project.getStartDate());
            projects.setEndDate(project.getEndDate());
            projects.setCreatedAt(LocalDateTime.now());
            Categories categories = categoryService.getCategory(project.getCategory_id());
            projects.setStatus(project.getStatus());

projects.setCreator(user);
            projects.setCategories(categories);
            if (image != null && !image.isEmpty()) { // kiểm tra reques có gửi ảnh hay không hoặc trống
                Files.createDirectories(Paths.get(UPLOAD_DIR)); // tạo thư mục để lưu ảnh
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // tên file gốc từ người dùng + thời gian hiện tại
                Path filePath = Paths.get(UPLOAD_DIR, fileName); // tạo đường dẫn đầy đủ Upload_dir+fileName
                Files.write(filePath, image.getBytes()); // Ghi dữ liệu vào file => chuyển dữ liệu ,ultipartfile thành byte
                projects.setImage(filePath.toString()); // lưu đường dẫn
            }
            projectsService.SaveProject(projects);
            Apireponsi<Projects> repon = new Apireponsi<>(HttpStatus.OK, "Created Project Thành Công", projects, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<Projects> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

        }
    }


    @PutMapping("/UpdateProject/{id}")
    public ResponseEntity<Apireponsi<Projects>> UpdateProjects(@ModelAttribute Project project
            , @PathVariable Long id
            , @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        try {
            Projects projects = projectsService.GetidProject(id);

            projects.setTitle(project.getTitle());
            projects.setDescription(project.getDescription());
            projects.setGoalAmount(project.getGoalAmount());
            projects.setPledgedAmount(project.getPledgedAmount());
            projects.setCollectedAmount(project.getCollectedAmount());
            projects.setStartDate(project.getStartDate());
            projects.setEndDate(project.getEndDate());
            projects.setCreatedAt(LocalDateTime.now());
            Categories categories = categoryService.getCategory(project.getCategory_id());

            projects.setCategories(categories);
            if (image != null && !image.isEmpty()) {
                Files.createDirectories(Paths.get(UPLOAD_DIR));
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.write(filePath, image.getBytes());
                projects.setImage(filePath.toString());

            }
            projectsService.SaveProject(projects);
            Apireponsi<Projects> repons = new Apireponsi<>(HttpStatus.OK, "Update Thành Công", projects, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repons);

        } catch (Exception e) {
            Apireponsi<Projects> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }

    }


    @DeleteMapping("/Delete/Projects/{id}")
    public ResponseEntity<Apireponsi<Projects>> DeleteProject(@PathVariable Long id) {
        try {
            Projects projects = projectsService.GetidProject(id);
            if (projects == null) {
                Apireponsi<Projects> erorr1 = new Apireponsi<>(HttpStatus.NOT_FOUND, " Tài khoản không tồn tại", null, null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erorr1);
            }
            projectsService.DeleteProject(id);
            Apireponsi<Projects> repon = new Apireponsi<>(HttpStatus.OK, "Delete Thành Công Dự án  " + projects.getTitle(), projects, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repon);


        } catch (Exception e) {
            Apireponsi<Projects> error = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

        }
    }

    @GetMapping("/GetAllProjects")
    public ResponseEntity<Apireponsi<List<Projects>>> GetAllProjects() {
        try {
            List<Projects> projects = projectsService.GetAllProjects();
            Apireponsi<List<Projects>> repon = new Apireponsi<>(HttpStatus.OK, "Get All Projects", projects, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Projects>> error1 = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }

    }

    @GetMapping("searchUser")
    public ResponseEntity<Apireponsi<List<Projects>>> searchUser(@RequestBody Keyword title) {
        try {
            List<Projects> projects = projectsService.SearchProject(title.getKeyword());
            Apireponsi<List<Projects>> repon = new Apireponsi<>(HttpStatus.OK, "Search User", projects, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Projects>> error1 = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Erorr", null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }
}
