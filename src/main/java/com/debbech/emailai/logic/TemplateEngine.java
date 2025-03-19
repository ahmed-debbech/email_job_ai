package com.debbech.emailai.logic;

import com.debbech.emailai.Config;
import com.debbech.emailai.SpringContext;
import com.debbech.emailai.model.WriteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Scanner;


public class TemplateEngine {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public String generate(int tempid, WriteRequest writeRequest){

        StringBuilder fileData = new StringBuilder();
        try {
            Config myconfig = SpringContext.getBean(Config.class);

            File myObj = new File(myconfig.getTemplatePath()+"/temp"+String.valueOf(tempid));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                fileData.append(data);
            }
            myReader.close();
        } catch (Exception e) {
            log.error("could not load template because: " + e.getMessage());
            return null;
        }

        String f = fileData.toString();
        if(f.isEmpty()) return null;

        if(writeRequest.getJob_desc() == null || writeRequest.getJob_desc().isEmpty()){
            f = f.replaceAll("&&job_desc", "not set ignore it");
        }else {
            f = f.replaceAll("&&job_desc", writeRequest.getJob_desc() + "\n");
        }

        if(writeRequest.getSet_mine() == null || writeRequest.getSet_mine().isEmpty()){
            f = f.replaceAll("&&set_mine", "not set ignore it");
        }else {
            f = f.replaceAll("&&set_mine", writeRequest.getSet_mine() + "\n");
        }

        return f;
    }


}
