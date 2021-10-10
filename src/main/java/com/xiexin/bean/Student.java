package com.xiexin.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * student
 * @author 
 */
@ToString //lombok的插件注解,不用写tostring
@Data //不用写get set...@Getter不用写getter @Setter,不用写setter
public class Student implements Serializable {
    private Integer studentId;

    private String studentName;

    private Integer studentSex;

    private String studentCardId;

    private String studentPhone;

    private String studentAddress;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "Gmt+8")
    private Date studentComeDate;

    private static final long serialVersionUID = 1L;

}