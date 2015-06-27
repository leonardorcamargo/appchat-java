package com.romano.model;

import com.romano.common.Type.TypeMethod;

/**
 *
 * @author Leonardo Camargo
 */
public class ServerMethodReturn {
    private TypeMethod typeMethod;
    private Object object;
    
    public void setTypeMethod(TypeMethod typeMethod){
        this.typeMethod = typeMethod;
    }
    
    public TypeMethod getTypeMethod(){
        return this.typeMethod;
    }
    
    public void setObject(Object object){
        this.object = object;
    }
    
    public Object getObject(){
        return this.object;
    }
}
