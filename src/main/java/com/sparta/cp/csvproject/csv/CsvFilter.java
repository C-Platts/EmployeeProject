package com.sparta.cp.csvproject.csv;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CsvFilter {
    
    public static ArrayList<String[]> getRecordsWithLengthTen(ArrayList<String> lines) {

        return (ArrayList<String[]>) lines.stream()
                .map(l -> l.split(","))
                .filter(a -> a.length == 10)
                .collect(Collectors.toList());
    }

    public static ArrayList<EmployeeDTO> getUniqueRecordsList(ArrayList<String[]> lines) {

        return (ArrayList<EmployeeDTO>) lines.stream()
            .map(EmployeeDTO::new)
                .distinct()
                .collect(Collectors.toList());
    }
}