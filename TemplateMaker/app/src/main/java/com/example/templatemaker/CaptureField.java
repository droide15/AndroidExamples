package com.example.templatemaker;

public class CaptureField {
    private String year;
    private int codeType;

    public CaptureField() {
    }

    public CaptureField(int codeType, String year) {
        this.codeType = codeType;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
}