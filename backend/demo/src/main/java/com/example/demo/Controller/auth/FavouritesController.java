package com.example.demo.Controller.auth;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.Entity.Favourites;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Entity.pledge;
import com.example.demo.Sercive.FavouritesService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class FavouritesController {
    @Autowired
    private FavouritesService favouritesService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectsService projectsService;
@PostMapping("/CreateFavourites/{id}")
    public ResponseEntity<Apireponsi<Favourites>> CreateFavourites(@PathVariable Long id){
    try {
        Favourites favourites = new Favourites();
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        System.out.println("user"+username);
        Users user = userService.getUserByUsername(username);
        favourites.setUser(user);
        Projects projects = projectsService.GetidProject(id);
        favourites.setProject(projects);
        favourites.setCreatedAt(LocalDateTime.now());
        favouritesService.SaveFavourites(favourites);
        Apireponsi<Favourites > repon = new Apireponsi<>(HttpStatus.CREATED,"Create  Thành Công Danh Sách yêu thích ",favourites,null);
        return ResponseEntity.status(HttpStatus.CREATED).body(repon);



    }catch (Exception e){
        Apireponsi<Favourites> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"erorr", null,null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
    }

}
@DeleteMapping("/DeleteFavourites/{id}")
    public ResponseEntity<Apireponsi<Favourites>> DeleteFavourites(@PathVariable Long id){
    try {
        Favourites favourites = favouritesService.GetFavourites(id);
        if(favourites==null){
            Apireponsi<Favourites> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"Không Tồn Tại",null,null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
        favouritesService.DeleteFavourites(id);
        Apireponsi<Favourites> repon = new Apireponsi<>(HttpStatus.OK,"Delete Thành Công",favourites,null);
        return ResponseEntity.status(HttpStatus.OK).body(repon);

    } catch (Exception e) {
        Apireponsi<Favourites> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"erorr", null,null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
    }
}
@PutMapping("/UpdateFavourites/{id}/{id_Project}")
    public ResponseEntity<Apireponsi<Favourites>> UpdateFavourites(@PathVariable Long id,@PathVariable Long id_Project){
    try {
        Favourites favourites = favouritesService.GetFavourites(id);
        if(favourites==null){
            Apireponsi<Favourites> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"Không Tồn Tại",null,null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }

        Projects projects1 = projectsService.GetidProject(id_Project);
        favourites.setProject(projects1);
        favourites.setCreatedAt(LocalDateTime.now());
        favouritesService.SaveFavourites(favourites);
       Apireponsi<Favourites> repon = new Apireponsi<>(HttpStatus.OK,"Update Thành Công",favourites,null);
return ResponseEntity.status(HttpStatus.OK).body(repon);
    }
    catch (Exception e){
        Apireponsi<Favourites> erorr = new Apireponsi<>(HttpStatus.BAD_REQUEST,"erorr", null,null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
    }
}

}
