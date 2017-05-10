package com.example.bootycall20.a5_3_1_dinner;

/**
 * Created by LucasVasquez on 5/8/17.
 */

public class ChoicesObject {

    public String choice1;
    public String choice2;
    public String choice3;
    public String choice4;
    public String choice5;

    public ChoicesObject () {}

    public ChoicesObject (String userChoice1, String userChoice2, String userChoice3,
                          String userChoice4, String userChoice5) {

        this.choice1 = userChoice1;
        this.choice2 = userChoice2;
        this.choice3 = userChoice3;
        this.choice4 = userChoice4;
        this.choice5 = userChoice5;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public void setChoice5(String choice5) {
        this.choice5 = choice5;
    }
}
