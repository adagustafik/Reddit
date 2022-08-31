package com.gfa.week19.repository;

import com.gfa.week19.model.Post;
import com.gfa.week19.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByUser(User user, Pageable pageable);

    @Modifying
    @Query(value = "update post set post.votes_count = post.votes_count + 1 where post.id= :id", nativeQuery = true)
    void upvotePost(@Param("id")Long id);

    @Modifying
    @Query(value = "update post set post.votes_count = post.votes_count - 1 where post.id= :id", nativeQuery = true)
    void downvotePost(@Param("id") Long id);
}
