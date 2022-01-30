package com.readingisgood.statisticservice.controller;

import com.readingisgood.statisticservice.dto.StatisticDto;
import com.readingisgood.statisticservice.entity.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@AllArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<StatisticDto>> listMonthlyOrderStatistics(@PathVariable("customerId") Long customerId){
        return ResponseEntity.ok(statisticService.listMonthlyOrderStatistics(customerId));
    }
}
