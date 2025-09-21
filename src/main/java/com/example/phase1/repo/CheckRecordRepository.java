package com.example.phase1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.phase1.model.CheckRecord;

public interface CheckRecordRepository extends JpaRepository<CheckRecord, Long> {
}
