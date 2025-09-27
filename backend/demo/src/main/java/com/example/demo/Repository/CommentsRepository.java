//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.demo.Repository;

import com.example.demo.Entity.Comments;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Comments c WHERE c.project.id = :projectId")
    void deleteByProjectId(Long projectId);

    @Modifying
    @Transactional
    @Query("UPDATE Comments c SET c.comment = :content, c.updated_at = CURRENT_TIMESTAMP WHERE c.user.userId = :userid AND c.comment_id = :id_comment")
    int updateComment(Long userid, String content, Long id_comment);

    @Modifying
    @Transactional
    @Query("select c FROM Comments c where c.user.userId=:id")
    List<Comments> findByUserId(Long id);

    @Modifying
    @Transactional
    @Query("select  c FROM  Comments  c where c.project.id=:id")
    List<Comments> findByProjectId(Long id);
}
