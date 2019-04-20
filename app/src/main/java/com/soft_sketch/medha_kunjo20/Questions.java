package com.soft_sketch.medha_kunjo20;

public class Questions {
    private String Question, option1,option2,option3,option4,category,strength;
    private int ansNumber;

    public Questions() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public Questions(String question, String option1, String option2, String option3, String option4, int ansNumber, String category, String strength) {
        Question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ansNumber = ansNumber;
        this.category = category;
        this.strength = strength;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getAnsNumber() {
        return ansNumber;
    }

    public void setAnsNumber(int ansNumber) {
        this.ansNumber = ansNumber;
    }
}
