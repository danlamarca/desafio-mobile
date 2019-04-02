package com.prototipo_danilo.tasks.infra.operation;

public class OperationResult<T> {
    //classe anonimo para todas as chamadas de assincronismo(threads) - classe flexivel para qualquer tipo de retorno

    public static final int NO_ERROR =-1;
    private int mError = NO_ERROR;
    private String mErrorMessage="";
    private T anonimousType;

    public int getError() {
        return mError;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setmError(int error, String message){
        this.mError=error;
        this.mErrorMessage=message;
    }

    public T getResult() {
        //setar a variavel como qualquer tipo.
        return anonimousType;
    }

    public void setResult(T anonimousType) {
        this.anonimousType = anonimousType;
    }
}
