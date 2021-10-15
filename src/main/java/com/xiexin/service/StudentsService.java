package com.xiexin.service;

import com.xiexin.bean.Students;
import com.xiexin.bean.StudentsDTO;
import com.xiexin.bean.StudentsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentsService {
   
    long countByExample(StudentsExample example);

    int deleteByExample(StudentsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Students record);

    int insertSelective(Students record);

    List<Students> selectByExample(StudentsExample example);

    Students selectByPrimaryKey(Integer id);
  
    int updateByExampleSelective(@Param("record") Students record, @Param("example") StudentsExample example);

    int updateByExample(@Param("record") Students record, @Param("example") StudentsExample example);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);

    int insertSelectiveDTO(StudentsDTO students);

    //两表联查角色
    List<Map> selectMore(Map map);
}
