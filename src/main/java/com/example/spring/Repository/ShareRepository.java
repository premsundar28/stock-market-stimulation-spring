package com.example.spring.repository;


import com.example.spring.entity.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share,Long> {

}