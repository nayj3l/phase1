package com.example.phase1.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.phase1.dto.CheckRequest;
import com.example.phase1.dto.CheckResponse;
import com.example.phase1.model.CheckRecord;
import com.example.phase1.repo.CheckRecordRepository;
import com.example.phase1.service.CheckService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CheckController {

    private final CheckService checkService;
    private final CheckRecordRepository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/check")
    public ResponseEntity<CheckResponse> check(@Valid @RequestBody CheckRequest request) {
        Integer value = request.getValue();

        CheckResponse resp = checkService.checkForC2(value);
        CheckResponse responseToReturn = resp != null ? resp : 
            CheckResponse.builder().a(null).b(null).c(null).avg(null).build();

        try {
            String responseJson = mapper.writeValueAsString(responseToReturn);

            CheckRecord checkRecord = CheckRecord
                    .builder()
                    .inputValue(value)
                    .responseJson(responseJson)
                    .createdAt(LocalDateTime.now())
                    .build();

            repo.save(checkRecord);

            return ResponseEntity.ok(responseToReturn);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/records")
    public ResponseEntity<List<CheckRecord>> getAllRecords() {
        return ResponseEntity.ok(repo.findAll());
    }
}
