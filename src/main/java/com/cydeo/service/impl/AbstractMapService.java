package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class AbstractMapService<T,ID> {
    HashMap<ID, T> map = new HashMap<>();
    T save(T t, ID id){
        map.put(id,t);
        return t;
    }
    T findById(ID id){
        return map.get(id);
    }
    List<T> findAll(){
        return new ArrayList<>(map.values());
    }

    void deleteById(ID id){
        map.remove(id);
    }
    void update(T t,ID id){
        map.put(id,t);
    }

}
