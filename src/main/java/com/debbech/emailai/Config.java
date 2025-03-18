package com.debbech.emailai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Value("${app.path_to_template}")
    public String path;

    public String getTemplatePath(){
        String filePath = path;
        filePath = filePath.replaceFirst("^~", System.getProperty("user.home"));
        return filePath;
    }

}
