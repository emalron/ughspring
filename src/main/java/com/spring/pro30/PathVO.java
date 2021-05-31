package com.spring.pro30;

import org.springframework.stereotype.Component;

@Component
public class PathVO {
    private String savePath;
    private String loadUrl;

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getLoadUrl() {
        return loadUrl;
    }

    public void setLoadUrl(String loadUrl) {
        this.loadUrl = loadUrl;
    }
}
