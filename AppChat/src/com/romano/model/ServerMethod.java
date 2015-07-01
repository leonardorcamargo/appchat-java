package com.romano.model;

import java.lang.reflect.Method;
import java.util.function.Function;

/**
 *
 * @author Leonardo Camargo
 */
public class ServerMethod {

    private String userPass;
    private Method method;
    private Object object;
    
    public void setUserPass(String userPass){
        this.userPass = userPass;
    }
    
    public String getUserPass(){
        return this.userPass;
    }
    
    public void setMethod(Method method){
        this.method = method;
    }
    
    public Method getMethod(){
        return this.method;
    }
    
    public void setObject(Object object){
        this.object = object;
    }
    
    public Object getObject(){
        return this.object;
    }
}
