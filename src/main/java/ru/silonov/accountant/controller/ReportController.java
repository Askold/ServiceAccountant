package ru.silonov.accountant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.silonov.accountant.api.DataProvider;
import ru.silonov.accountant.model.ReportDTO;
import ru.silonov.accountant.model.ReportEntity;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private static final DataProvider dataProvider = new DataProvider();
    private static final Logger logger = LogManager.getLogger(ReportController.class);

    @GetMapping("/today")
    public List<ReportEntity> selectToday() {
        List<ReportEntity> list = dataProvider.selectCurrent();
        logger.info(list);
        return list;
    }


    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ReportEntity postReport(@RequestBody ReportDTO report){
        ReportEntity entity =
                new ReportEntity( report.getTime(), report.getTask(), report.getUserId());
        logger.info(entity);
        dataProvider.insert(entity);
        return entity;
    }
}
