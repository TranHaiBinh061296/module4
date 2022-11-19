package com.codegym.repository;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends PagingAndSortingRepository<Blog, Long> {

    Iterable<Blog> findAllByCategory(Category category);

    Page<Blog> findAllByTitleContaining(String title,  Pageable pageable);

    List<Blog> findAllByIdOrTitle(Long id, String title);

    @Query(value = "SELECT * FROM blogs WHERE id LIKE :keySearch OR title LIKE :keySearch " , nativeQuery = true)
    List<Blog> findAllByIdOrTitleNavtiveQuery(@Param("keySearch") String keySearch);

}
