package com.debbech.emailai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${app.path_to_template}")
    public String path;
    private boolean config_is_set = false;
    private String ollamaIp;
    private String ollamaModel;


    public String getTemplatePath(){
        String filePath = path;
        filePath = filePath.replaceFirst("^~", System.getProperty("user.home"));
        return filePath;
    }

    public String getOllamaIp() {
        return ollamaIp;
    }

    public void setOllamaIp(String ollamaIp) {
        this.ollamaIp = ollamaIp;
    }

    public String getOllamaModel() {
        return ollamaModel;
    }

    public void setOllamaModel(String ollamaModel) {
        this.ollamaModel = ollamaModel;
    }

    public boolean isConfigSet(){
        return this.config_is_set;
    }

    public void configIsSet(boolean set){
        this.config_is_set = set;
    }
}
