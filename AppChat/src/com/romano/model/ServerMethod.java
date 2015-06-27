package com.romano.model;

import com.romano.common.Type.TypeMethod;
import java.util.function.Function;

/**
 *
 * @author Leonardo Camargo
 */
public class ServerMethod {

    private String userPass;
    private Function<Object,Object> function;
    private Object object;
    
    public void setUserPass(String userPass){
        this.userPass = userPass;
    }
    
    public String getUserPass(){
        return this.userPass;
    }
    
    public void setFunction(Function<Object,Object> function){
        this.function = function;
    }
    
    public Function<Object, Object> getFunction(){
        return this.function;
    }
    
    public void setObject(Object object){
        this.object = object;
    }
    
    public Object getObject(){
        return this.object;
    }
}
