package com.example.demo.Controller.Owner;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.Keyword;
import com.example.demo.DTO.Project;
import com.example.demo.Entity.Categories;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.AuthService;
import com.example.demo.Sercive.CategoryService;
import com.example.demo.Sercive.CommentsService;
import com.example.demo.Sercive.FavouritesService;
import com.example.demo.Sercive.PayoutService;
import com.example.demo.Sercive.PledgeService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.RewardService;
import com.example.demo.Sercive.UserService;

import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/api/Projects"})
@RequiredArgsConstructor
public class OwnerProjectController {

    private final ProjectsService projectsService;

    private final CategoryService categoryService;

    private final UserService userService;

    private final AuthService roleservice;

    private final AuthService authService;

    private final CommentsService commentsService;

    private final RewardService rewardService;

    private final PledgeService pledgeService;

    private final PayoutService payoutService;

    private FavouritesService favouritesService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final String UPLOAD_DIR = "uploads/projects/";


    @DeleteMapping({"/Delete/{id}"})
    public ResponseEntity<Apireponsi<Projects>> DeleteProject(@PathVariable Long id) {
        try {
            Projects projects = this.projectsService.GetidProject(id);
            if (projects == null) {
                Apireponsi<Projects> erorr1 = new Apireponsi(HttpStatus.NOT_FOUND, " Tài khoản không tồn tại", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erorr1);
            } else {
                this.pledgeService.deleteProjects(id);
                this.favouritesService.DeleteProjects(id);
                this.commentsService.deleteAllByProjectId(id);
                this.rewardService.deleteProjects(id);
                this.payoutService.DeleteProjects(id);
                this.projectsService.DeleteProject(id);
                Apireponsi<Projects> repon = new Apireponsi(HttpStatus.OK, "Delete Thành Công Dự án  " + projects.getTitle(), projects, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception var4) {
            Apireponsi<Projects> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PostMapping({"/create"})
    public ResponseEntity<Apireponsi<Projects>> CreateProject(@ModelAttribute Project project, @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            Users user = this.userService.getUserByUsername(username);
            Categories categories = this.categoryService.getCategory(project.getCategory_id());

            Projects projects = new Projects().builder()
                    .title(project.getTitle())
                    .description(project.getDescription())
                    .goalAmount(project.getGoalAmount())

                    .startDate(project.getStartDate())
                    .endDate(project.getEndDate())
                    .createdAt(LocalDateTime.now())
                    .status(Projects.statusprojects.PENDING)
                    .creator(user)
                    .categories(categories)


                    .build();
            if (image != null && !image.isEmpty()) {
                Files.createDirectories(Paths.get("uploads/projects/"));
                long var10000 = System.currentTimeMillis();
                String fileName = var10000 + "_" + image.getOriginalFilename();
                Path filePath = Paths.get("uploads/projects/", fileName);
                Files.write(filePath, image.getBytes(), new OpenOption[0]);
                projects.setImage(filePath.toString());
            }

            this.projectsService.SaveProject(projects);
            Apireponsi<Projects> repon = new Apireponsi(HttpStatus.OK, "Created Project Thành Công", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception var10) {
            Apireponsi<Projects> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping({"/update/{id}"})
    public ResponseEntity<Apireponsi<Projects>> UpdateProjects(@ModelAttribute Project project, @PathVariable Long id, @RequestParam(value = "image", required = false) MultipartFile image) throws Exception {
        try {
            Projects projects = this.projectsService.GetidProject(id);
            if (projects.getStatus().equals("OPEN")) {
                Apireponsi<Projects> Erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST, " Dự án đã được duyệt không có quyền chỉnh sữa", projects, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Erorr);
            }
            projects.setTitle(project.getTitle());
            projects.setDescription(project.getDescription());
            projects.setGoalAmount(project.getGoalAmount());

            projects.setEndDate(project.getEndDate());
            projects.setCreatedAt(LocalDateTime.now());
            Categories categories = this.categoryService.getCategory(project.getCategory_id());
            projects.setCategories(categories);
            if (image != null && !image.isEmpty()) {
                Files.createDirectories(Paths.get("uploads/projects/"));
                long var10000 = System.currentTimeMillis();
                String fileName = var10000 + "_" + image.getOriginalFilename();
                Path filePath = Paths.get("uploads/projects/", fileName);
                Files.write(filePath, image.getBytes(), new OpenOption[0]);
                projects.setImage(filePath.toString());
            }

            this.projectsService.SaveProject(projects);
            Apireponsi<Projects> repons = new Apireponsi(HttpStatus.OK, "Update Thành Công", projects, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repons);
        } catch (Exception var8) {
            Apireponsi<Projects> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @GetMapping({"/GetAllProjects"})
    public ResponseEntity<Apireponsi<List<Projects>>> GetAllProjects() {
        try {
            List<Projects> projects = this.projectsService.GetAllProjects();
            Apireponsi<List<Projects>> repon = new Apireponsi(HttpStatus.OK, "Get All Projects", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Projects>> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }

    @GetMapping({"searchUser"})
    public ResponseEntity<Apireponsi<List<Projects>>> searchUser(@RequestBody Keyword title) {
        try {
            List<Projects> projects = this.projectsService.SearchProject(title.getKeyword());
            Apireponsi<List<Projects>> repon = new Apireponsi(HttpStatus.OK, "Search User", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<Projects>> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Apireponsi<Projects>> list(@PathVariable Long id) {
        try {
            Projects projects = projectsService.GetidProject(id);
            if (projects.getCategories() != null) {
                Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);

            }
            Apireponsi<Projects> repon = new Apireponsi<>(HttpStatus.OK, " Projects", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }

    @GetMapping("/MyProẹcts")
    public ResponseEntity<Apireponsi<List<Projects>>> getMyProjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userService.getUserByUsername(username);
        try {
            List<Projects> projects = this.projectsService.getMyProjects(user.getUserId());
            Apireponsi<List<Projects>> repon = new Apireponsi(HttpStatus.OK, "My Projects", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception e) {
            Apireponsi<List<Projects>> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }

    @PutMapping("/Open/{id}")
    public ResponseEntity<Apireponsi<Projects>> OpenProject(@PathVariable Long id) {
        try {
            Projects projects = projectsService.GetidProject(id);
            if (projects == null) {
                Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            }
            if (!projects.getStatus().equals(Projects.statusprojects.PENDING)) {
                Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Chỉ có dự án mới tạo mới đợi duyệt", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            }
            projects.setStatus(Projects.statusprojects.OPEN);
            projectsService.SaveProject(projects);
            Apireponsi<Projects> repon = new Apireponsi<>(HttpStatus.OK, "Open Projects", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {

            Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }

    @PutMapping("/rejected/{id}")
    public ResponseEntity<Apireponsi<Projects>> rejectedProject(@PathVariable Long id) {
        try {
            Projects projects = projectsService.GetidProject(id);
            if (projects == null) {
                Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            }
            if (!projects.getStatus().equals(Projects.statusprojects.PENDING)) {
                Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Chỉ có dự án mới từ chối dự án", (Object) null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
            }
            projects.setStatus(Projects.statusprojects.REJECTED);
            projectsService.SaveProject(projects);
            Apireponsi<Projects> repon = new Apireponsi<>(HttpStatus.OK, "REJECTED Projects", projects, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {

            Apireponsi<Projects> error1 = new Apireponsi(HttpStatus.BAD_REQUEST, "Erorr", (Object) null, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error1);
        }
    }
}





