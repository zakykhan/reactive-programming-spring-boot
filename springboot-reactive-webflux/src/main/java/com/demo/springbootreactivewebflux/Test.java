package com.demo.springbootreactivewebflux;


public class Test {

    public static void main(String[] args) {

        String str = "intellij";

        //StringBuilder stringBuilder = reverseString(str);
        System.out.println(reverseString(str));
    }

    public static String reverseString(String str){
        StringBuilder str1 = new StringBuilder();

        str1.append(str);

        StringBuilder temp = new StringBuilder();

        for(int i = str.length() - 1; i >= 0; i--){
            temp.append(str.charAt(i));
        }

        return temp.toString();
    }

}
