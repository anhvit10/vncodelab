package com.vncodelab.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vncodelab.entity.Cate;

@Repository
public interface CateRespository extends JpaRepository<Cate, Integer> {

	// List<Cate> findAllByMore(boolean isMore);

	Optional<Cate> findByCateID(Integer cateID);

	List<Cate> findAllByType(Integer type);

	Page<Cate> findAll(Pageable pageable);
}