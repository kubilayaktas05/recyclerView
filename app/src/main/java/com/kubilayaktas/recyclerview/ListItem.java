package com.kubilayaktas.recyclerview;

/**
 * Created by MSI on 19.07.2018 at 16:32.
 */
public class ListItem {
    private String Head;
    private String Description;

    public ListItem(String head, String description) {
        Head = head;
        Description = description;
    }

    public String getHead() {
        return Head;
    }

    public String getDescription() {
        return Description;
    }
}
