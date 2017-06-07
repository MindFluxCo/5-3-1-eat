package com.crevas.and.go_5_3_1;


public class ChoicesObject {

    public ChoicesDetail choice1;
    public ChoicesDetail choice2;
    public ChoicesDetail choice3;
    public ChoicesDetail choice4;
    public ChoicesDetail choice5;
    public ChoicesDetail finalChoice;



    public ChoicesObject () {}

    public ChoicesObject(ChoicesDetail userChoice1, ChoicesDetail userChoice2,
                         ChoicesDetail userChoice3,
                         ChoicesDetail userChoice4, ChoicesDetail userChoice5, ChoicesDetail finalChoice) {

        this.choice1 = userChoice1;
        this.choice2 = userChoice2;
        this.choice3 = userChoice3;
        this.choice4 = userChoice4;
        this.choice5 = userChoice5;
    }

    public ChoicesDetail getChoice1() {
        return choice1;
    }

    public void setChoice1(ChoicesDetail choice1) {
        this.choice1 = choice1;
    }

    public ChoicesDetail getChoice2() {
        return choice2;
    }

    public void setChoice2(ChoicesDetail choice2) {
        this.choice2 = choice2;
    }

    public ChoicesDetail getChoice3() {
        return choice3;
    }

    public void setChoice3(ChoicesDetail choice3) {
        this.choice3 = choice3;
    }

    public ChoicesDetail getChoice4() {
        return choice4;
    }

    public void setChoice4(ChoicesDetail choice4) {
        this.choice4 = choice4;
    }

    public ChoicesDetail getChoice5() {
        return choice5;
    }

    public void setChoice5(ChoicesDetail choice5) {
        this.choice5 = choice5;
    }

    public ChoicesDetail getFinalChoice() {
        return finalChoice;
    }

    public void setFinalChoice(ChoicesDetail finalChoice) {
        this.finalChoice = finalChoice;
    }
}
