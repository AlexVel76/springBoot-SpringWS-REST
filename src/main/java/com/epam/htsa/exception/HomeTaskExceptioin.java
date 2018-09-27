package com.epam.htsa.exception;

public class HomeTaskExceptioin extends RuntimeException {

    private static final long serialVersionUID = -7252581970876930056L;
    private String exceptionMsg;

    public HomeTaskExceptioin(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

}
