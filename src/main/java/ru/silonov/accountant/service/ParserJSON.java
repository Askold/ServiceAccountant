package ru.silonov.accountant.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.silonov.accountant.model.ReportEntityDTO;

import java.util.Arrays;
import java.util.List;

public class ParserJSON {
    public static List toParse (String str, Class<ReportEntityDTO[]> valueType) {
        List<ReportEntityDTO> list = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            list = Arrays.asList(objectMapper.readValue(str, valueType));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
