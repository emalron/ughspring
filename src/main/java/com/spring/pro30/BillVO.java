package com.spring.pro30;

public class BillVO {
    private String item;
    private int qty;
    public BillVO() {};
    public BillVO(String item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
