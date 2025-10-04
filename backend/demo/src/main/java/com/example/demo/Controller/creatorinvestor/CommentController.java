//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Controller.creatorinvestor;


import com.example.demo.DTO.Apireponsi;
import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.CommentDTO1;
import com.example.demo.Entity.Comments;
import com.example.demo.Entity.Projects;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.CommentsService;
import com.example.demo.Sercive.ProjectsService;
import com.example.demo.Sercive.UserService;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/Comment/"})
@RequiredArgsConstructor
public class CommentController {

    private final CommentsService commentsService;

    private final UserService userService;

    private final ProjectsService projectsService;


    @PostMapping({"/Create"})
    @Transactional
    public ResponseEntity<Apireponsi<Comments>> CreateComment(@RequestBody CommentDTO commentDTO) {
        if (commentDTO != null && commentDTO.getComment() != null && commentDTO.getProjects_id() != null) {
            try {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String username = auth.getName();
                Users user = this.userService.getUserByUsername(username);
                if (user == null) {
                    Apireponsi<Comments> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại người dùng", (Object) null, (String) null);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
                } else {
                    Projects projects = this.projectsService.GetidProject(commentDTO.getProjects_id());
                    if (projects == null) {
                        Apireponsi<Comments> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại dự án", (Object) null, (String) null);
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
                    } else {
                        Comments comments = new Comments().builder()
                                .comment(commentDTO.getComment())
                                .project(projects)
                                .updated_at(LocalDateTime.now())
                                .user(user)
                                .updated_at(LocalDateTime.now())
                                .build();

                        this.commentsService.SaveComment(comments);
                        Apireponsi<Comments> response = new Apireponsi(HttpStatus.CREATED, "Tạo Comment thành công", comments, (String) null);
                        return ResponseEntity.status(HttpStatus.CREATED).body(response);
                    }
                }
            } catch (Exception var8) {
                Apireponsi<Comments> error = new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
            }
        } else {
            Apireponsi<Comments> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Dữ liệu không hợp lệ", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    @PutMapping({"/Update"})
    public ResponseEntity<Apireponsi<Comments>> UpdateComment(@RequestBody CommentDTO1 commentDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user1 = this.userService.getUserByUsername(auth.getName());

        try {
            Long id_user = user1.getUserId();
            String content = commentDTO.getComment();
            Long id_comment = commentDTO.getComment_id();
            this.commentsService.UpdateComment(id_user, content, id_comment);
            Comments comments = this.commentsService.getCommentById(id_comment);
            if (comments == null) {
                Apireponsi<Comments> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Không tồn tại Comment", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else if (id_user != comments.getUser().getUserId()) {
                Apireponsi<Comments> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Không có quyền thay đổi content người khác", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else {
                comments.setComment(commentDTO.getComment());
                comments.setUpdated_at(LocalDateTime.now());
                Apireponsi<Comments> repon = new Apireponsi(HttpStatus.OK, "Cập Nhập Thành Công", comments, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception var9) {
            Apireponsi<Comments> error = new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping({"/Delete/{id}"})
    public ResponseEntity<Apireponsi<Comments>> DeleteComment(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Users user1 = this.userService.getUserByUsername(auth.getName());
        Long id_user = user1.getUserId();

        try {
            Comments comments = this.commentsService.getCommentById(id);
            if (id_user != comments.getUser().getUserId()) {
                Apireponsi<Comments> error = new Apireponsi(HttpStatus.BAD_REQUEST, "Không có quyền Delete content người khác", (Object) null, (String) null);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            } else {
                this.commentsService.DeleteComment(id);
                Apireponsi<Comments> repon = new Apireponsi(HttpStatus.OK, "Delete Thành Công", comments, (String) null);
                return ResponseEntity.status(HttpStatus.OK).body(repon);
            }
        } catch (Exception var7) {
            Apireponsi<Comments> error = new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping({"/Getall/{projects_id}"})
    public ResponseEntity<Apireponsi<List<Comments>>> getAllComment(@PathVariable Long projects_id) {
        try {
            List<Comments> listComments = this.commentsService.getCommentsByProjectId(projects_id);
            Apireponsi<List<Comments>> repon = new Apireponsi(HttpStatus.OK, "Get All Comment", listComments, (String) null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception var4) {
            Apireponsi<List<Comments>> error = new Apireponsi(HttpStatus.INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
