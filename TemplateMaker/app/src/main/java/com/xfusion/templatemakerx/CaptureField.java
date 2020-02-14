package com.xfusion.templatemakerx;

public class CaptureField {
    private boolean answering;
    private int codeType;

    public CaptureField(int codeType) {
        this.answering = false;
        this.codeType = codeType;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }

    public boolean isAnswering() {
        return answering;
    }

    public void setAnswering(boolean answering) {
        this.answering = answering;
    }
}