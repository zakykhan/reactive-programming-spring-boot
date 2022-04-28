package com.demo.reactive.problemSolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        List<Integer> grades = Arrays.asList(36,
                6,
                100,
                25,
                97,
                24,
                25,
                70,
                50,
                71,
                30,
                14,
                28,
                100,
                3,
                26,
                61,
                100,
                50,
                41,
                5,
                3,
                28,
                34,
                0);

        /*80
        96
        18
        75
        80
        60
        60
        15
        45
        15
        10
        5
        46
        87
        33
        60
        14
        71
        65
        2
        5
        97
        0*/
       /* grades.add(73);
        grades.add(67);
        grades.add(38);
        grades.add(33);
*/

        List<Integer> integers = gradingStudents(grades);

        System.out.println(Arrays.asList(integers));

    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> result = new ArrayList<>();
        for(Integer i : grades){
            if(i < 38){
                result.add(i);
            }
            int roundedNumber =  Math.round(i/5 + 1) * 5;
            System.out.println(roundedNumber + "    Rounded num");
            if((roundedNumber - i) < 3 && i >= 38){
                result.add(roundedNumber);
            }

            if((roundedNumber - i) >= 3){
                result.add(i);
            }
        }
        return result;
    }
}
