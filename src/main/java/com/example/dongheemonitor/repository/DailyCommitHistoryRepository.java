package com.example.dongheemonitor.repository;

import com.example.dongheemonitor.entity.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface DailyCommitHistoryRepository extends MongoRepository<DailyCommitHistory, String> {
}
