package com.sparta.cp.csvproject;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvFilter {


    public ArrayList<String[]> filterLinesLength(ArrayList<String> lines) {

        return (ArrayList<String[]>) lines.stream()
                .map(l -> l.split(","))
                .filter(a -> a.length == 10)
                .collect(Collectors.toList());
    }

    public ArrayList<EmployeeDTO> filterDuplicates(ArrayList<String[]> lines) {

        return (ArrayList<EmployeeDTO>) lines.stream()
            .map(EmployeeDTO::new)
                .distinct()
                .collect(Collectors.toList());
    }

    //Backup
/*    employeeDTOList = bufferedReader.lines()
            .map(l -> l.split(","))
            .filter(a -> a.length == 10)
            .map(EmployeeDTO::new)
                        .filter(Objects::nonNull)
                        .distinct()
                        .collect(Collectors.toList());*/

}