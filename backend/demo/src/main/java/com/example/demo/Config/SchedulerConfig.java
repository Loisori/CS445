package com.example.demo.Config;

import com.example.demo.Sercive.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {

    private final ProjectsService projectService;

    // Chạy mỗi 1 phút để check project hết hạn
    @Scheduled(fixedRate = 60000)
    public void checkExpiredProjects() {
        projectService.checkcontractterm();
        System.out.println("✅ Check checkExpiredProjects  " + LocalDateTime.now());
    }
}
