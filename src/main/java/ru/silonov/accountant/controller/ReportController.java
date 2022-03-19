package ru.silonov.accountant.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.silonov.accountant.api.DataProvider;
import ru.silonov.accountant.model.ReportEntity;

import java.util.ArrayList;
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
}
