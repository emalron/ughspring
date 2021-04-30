package com.spring.pro30;

public class PageVO {
    private int current;
    private int begin;
    private int end;

    public PageVO(int current, int begin, int end) {
        this.current = current;
        this.begin = begin;
        this.end = end;
    }
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
