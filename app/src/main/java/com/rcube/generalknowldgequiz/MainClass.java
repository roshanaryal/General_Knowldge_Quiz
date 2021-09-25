package com.rcube.generalknowldgequiz;

import java.util.ArrayList;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        List<QuestionModal> questionModalArrayListForOneCategory=new ArrayList<>();
        //add question answer option and answer like this
        questionModalArrayListForOneCategory.add(new QuestionModal("Question","optiona","optionb","optionc","optiond","correctanswer","category"));
        //add other question like this
        //make sure write answer option and answer should be same
        //if cat is write answer option answer should be same as cat, it is case sensetive
        //name of category should be exatly same for all question in one category

        List<QuestionModal> questionModalArrayListForAnotherCategory=new ArrayList<>();
        questionModalArrayListForAnotherCategory.add(new QuestionModal("Question","optiona","optionb","optionc","optiond","correctanswer","category"));


    }
}
