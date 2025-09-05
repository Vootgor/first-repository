package com.bookstore.entity.enums;


public enum EvaluationOfBook {
    NO_GRADES   ("0"),
    ONE         ("1"),
    TWO         ("2"),
    THREE       ("3"),
    FOUR        ("4"),
    FIVE        ("5"),
    SIX         ("6"),
    SEVEN       ("7"),
    EIGHT       ("8"),
    NINE        ("9"),
    TEN         ("10");

    private final String evaluation;

    EvaluationOfBook(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluation() {
        return evaluation;
    }
}
