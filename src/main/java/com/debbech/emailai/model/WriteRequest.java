package com.debbech.emailai.model;


public class WriteRequest {
    private String job_link;
    private String name;
    private String job_desc;
    private String set_mine;
    private int template_num;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "job_link='" + job_link + '\'' +
                ", name='" + name + '\'' +
                ", job_desc='" + job_desc + '\'' +
                ", set_mine='" + set_mine + '\'' +
                ", template_num=" + template_num +
                '}';
    }

    public String getJob_link() {
        return job_link;
    }

    public void setJob_link(String job_link) {
        this.job_link = job_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob_desc() {
        return job_desc;
    }

    public void setJob_desc(String job_desc) {
        this.job_desc = job_desc;
    }

    public String getSet_mine() {
        return set_mine;
    }

    public void setSet_mine(String set_mine) {
        this.set_mine = set_mine;
    }

    public int getTemplate_num() {
        return template_num;
    }

    public void setTemplate_num(int template_num) {
        this.template_num = template_num;
    }

}
