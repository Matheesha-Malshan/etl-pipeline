package org.example.transformer.chain;


public abstract class TransformationHandler {

    public TransformationHandler nextHandler;

    public void setNextHandler(TransformationHandler handler){
        this.nextHandler=handler;
    }

    public Object handle(Object input){
        Object result=process(input);
        if (result==null){
            return 0;
        }
        if (nextHandler!=null){
            return nextHandler.handle(result);
        }
        return result;
    }

    public abstract Object process(Object input);
}
