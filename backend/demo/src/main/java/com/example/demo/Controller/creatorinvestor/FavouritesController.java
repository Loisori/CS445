//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Controller.creatorinvestor;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.Entity.Favourites;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.FavouritesService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.UserService;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/favourites"})
@RequiredArgsConstructor
public class FavouritesController {

    private final FavouritesService favouritesService;

    private final UserService userService;

    private final ProjectsService projectsService;


    @PostMapping({"/create/{id}"})
    public ResponseEntity<Apireponsi<Favourites>> CreateFavourites(@PathVariable Long id) {
        try {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            Users user = this.userService.getUserByUsername(username);

            Projects projects = this.projectsService.GetidProject(id);

            Favourites favourites1 = new Favourites().builder()
                    .createdAt(LocalDateTime.now())
                    .user(user)
                    .project(projects)
                    .build();
            this.favouritesService.SaveFavourites(favourites1);
            Apireponsi<Favourites> repon = new Apireponsi(HttpStatus.CREATED, "Create  Thành Công Danh Sách yêu thích ", favourites1, (String) null);
            return ResponseEntity.status(HttpStatus.CREATED).body(repon);
        } catch (Exception var8) {
            Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @DeleteMapping({"/delete/{id}"})
    public ResponseEntity<Apireponsi<Favourites>> DeleteFavourites(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = this.userService.getUserByUsername(username);
        try {
            Favourites favourites = this.favouritesService.GetFavourites(id);
            if (favourites == null) {
                Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không Tồn Tại", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            if (!user.getUserId().equals(favourites.getUser().getUserId())) {
                Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không có quyền xoá", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            } else {
                this.favouritesService.DeleteFavourites(id);
                Apireponsi<Favourites> repon = new Apireponsi(HttpStatus.OK, "Delete Thành Công", favourites, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception var4) {
            Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping("/my")
    public ResponseEntity<Apireponsi<List<Favourites>>> GetMyFavourites() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = this.userService.getUserByUsername(username);
        try {
            List<Favourites> favourites = this.favouritesService.GetUser_id(user.getUserId());

            Apireponsi<List<Favourites>> repon = new Apireponsi<>(HttpStatus.OK, "Danh sách của tôi", favourites, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception var4) {
            Apireponsi<List<Favourites>> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }

    }

    @PutMapping({"/update/{id}/{id_Project}"})
    public ResponseEntity<Apireponsi<Favourites>> UpdateFavourites(@PathVariable Long id, @PathVariable Long id_Project) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = this.userService.getUserByUsername(username);
        try {
            Favourites favourites = this.favouritesService.GetFavourites(id);
            if (favourites == null) {
                Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "Không Tồn Tại", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
            }
            if (!user.getUserId().equals(favourites.getUser().getUserId())) {
                Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.FORBIDDEN, "Không được quyền", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erorr);
            }
            Projects projects1 = this.projectsService.GetidProject(id_Project);
            favourites.setProject(projects1);
            favourites.setCreatedAt(LocalDateTime.now());
            this.favouritesService.SaveFavourites(favourites);
            Apireponsi<Favourites> repon = new Apireponsi(HttpStatus.OK, "Update Thành Công", favourites, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);

        } catch (Exception var6) {
            Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Apireponsi<Favourites>> GetFavourites(@PathVariable Long id) {
        Authentication athu = SecurityContextHolder.getContext().getAuthentication();
        String username = athu.getName();
        Users user = userService.getUserByUsername(username);

        try {
            Favourites favourites = this.favouritesService.GetFavourites(id);
            if (favourites == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Apireponsi<>(HttpStatus.NOT_FOUND, "Không tìm thấy favourites", null, null));
            }
            if (!user.getUserId().equals(favourites.getUser().getUserId())) {
                Apireponsi<Favourites> repon = new Apireponsi<>(HttpStatus.BAD_REQUEST, "Không có quyền ", null, null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(repon);
            } else {
                Apireponsi<Favourites> repon = new Apireponsi<>(HttpStatus.OK, "Lấy thành Công", favourites, null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception var6) {
            Apireponsi<Favourites> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
}
