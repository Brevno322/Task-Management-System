package com.artmuh.taskmanagementsystem.mapper;

public interface Mapper <F,T>{

    T map(F fromObjet);

    default T map(F fromObject,T toObject){
        return toObject;
    }
}
