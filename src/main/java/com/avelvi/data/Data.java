package com.avelvi.data;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Data {

    private String fileName;
    private long count;
    private int level;
    private boolean isFile;
    private List<Data> children;

    public Data(String fileName, long count, int level, boolean isFile) {
        this.fileName = fileName;
        this.count = count;
        this.level = level;
        this.isFile = isFile;
        this.children = new LinkedList<>();
    }

    public void displayData() {
        System.out.println(this);
        this.children.forEach(Data::displayData);
    }

    public String getFileName() {
        return fileName;
    }

    public List<Data> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

    public void setChildren(List<Data> children) {
        this.children = children;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public boolean isFile() {
        return isFile;
    }

    @Override
    public String toString() {
        return String.join("", Collections.nCopies(level, "\t")) + this.fileName + " : " + this.count;
    }

}
