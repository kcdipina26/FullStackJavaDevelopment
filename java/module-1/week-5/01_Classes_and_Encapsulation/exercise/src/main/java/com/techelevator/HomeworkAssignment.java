package com.techelevator;

public class HomeworkAssignment {
    private  int earnedMarks;
    private int possibleMarks;
    private String submitterName;

    public HomeworkAssignment(int possibleMarks, String submitterName){
        this.possibleMarks = possibleMarks;
        this.submitterName = submitterName;

    }
    public int getEarnedMarks(){
        return earnedMarks;
    }
public void setEarnedMarks(int earnedMarks){
        this.earnedMarks= earnedMarks;
}

    public int getPossibleMarks() {
        return possibleMarks;
    }

    public String getSubmitterName() {
        return submitterName;
    }
    public String getLetterGrade(){
        double percentage = (double) earnedMarks / possibleMarks * 100;
        if (percentage >= 90) {
            return "A";

        } else if (percentage >=80) {
            return "B";
        }else if (percentage>=70) {
            return "C";
        } else if (percentage >=60) {
            return "D";
        } else {
            return "F";
        }
    }
}
