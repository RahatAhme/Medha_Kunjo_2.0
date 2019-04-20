package com.soft_sketch.medha_kunjo20;

import android.provider.BaseColumns;

public final class DB_Content {

    private DB_Content(){}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "Question_Table";
        public static final String COLOUM_QUESTION = "Question";
        public static final String COLOUM_OPTION1 = "Option1";
        public static final String COLOUM_OPTION2 = "Option2";
        public static final String COLOUM_OPTION3 = "Option3";
        public static final String COLOUM_OPTION4 = "Option4";
        public static final String COLOUM_ANS_NUM= "Ans_num";
        public static final String COLOUM_CATEGORY = "Category";
        public static final String COLOUM_STRENGTH = "Strength";


    }
}
