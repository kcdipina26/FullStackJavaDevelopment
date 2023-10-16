package com.lendingcatalog.model;

import java.util.UUID;

public class Tool implements CatalogItem {
    private String id;
    private String type;
    private String manufacturer;
    private int count;

    public Tool(String type, String manufacturer, int count) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.count = count;
    }

    @Override
    public boolean matchesName(String searchStr) {
        return type.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return manufacturer.toLowerCase().contains(searchStr.toLowerCase());
    }

    @Override
    public boolean matchesYear(int searchYear) {
        // Tool has no year field, so this method must only return false.
        return false;
    }

    @Override
    public void registerItem() {
        this.id = UUID.randomUUID().toString();
        // TODO: Add logic to write to a log file.
    }

    @Override
    public String toString() {
        return "* " + type + System.lineSeparator()
                + " - Made by: " + manufacturer + System.lineSeparator()
                + " - Count: " + count + System.lineSeparator()
                + " - Id: " + id;
    }
}
