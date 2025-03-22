package com.debbech.emailai.model;

public class ConfigModel {
    private String ollamaIp;
    private String ollamaModel;
    private String temp1;

    @Override
    public String toString() {
        return "ConfigModel{" +
                "ollamaIp='" + ollamaIp + '\'' +
                ", ollamaModel='" + ollamaModel + '\'' +
                ", temp1='" + temp1 + '\'' +
                '}';
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

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }
}
