package com.xiexin.bean;

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
public class StudentDTO implements Serializable {
    private Integer studentId;

    private String studentName;

    private Integer studentSex;

    private String studentCardId;

    private String studentPhone;

    private String studentAddress;
    private Date studentComeDate;

    private static final long serialVersionUID = 1L;

}