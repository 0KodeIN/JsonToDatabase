package com.example.jsontodatabase;

public class Request {
    private long id_request;
    private  String name;
    private String description;
    public int value = 2;
    public  Request (long id_request, String name, String desc){
        this.id_request = id_request;
        this.name = name;
        this.description = desc;
    }

}
