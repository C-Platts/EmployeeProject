package com.sparta.cp.csvproject.jdbc.util;

import com.sparta.cp.csvproject.dto.EmployeeDTO;

import java.util.LinkedList;
import java.util.List;

public class ListSplitter {

    public static <T> LinkedList<List<T>> split(List<T> list, int segmentSize) {

        LinkedList<List<T>> segments = new LinkedList<>();

        for (int i = 0 ; i < list.size(); i+= segmentSize) {
            segments.add(
                    list.subList(i, Math.min(i + segmentSize, list.size()))
            );
        }

        return segments;

    }

}
