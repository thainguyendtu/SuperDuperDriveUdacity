package com.udacity.jwdnd.course1.superduperdriver.model.mapper;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE delFlag = 0")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILES WHERE id = #{id} AND delFlag = 0")
    File getFileDetail(int id);

    @Select("SELECT * FROM FILES WHERE fileName = #{fileName} AND userId = #{userId} AND delFlag = 0")
    File checkExistFileName(String fileName, int userId);

    @Insert("INSERT INTO FILES (fileName, contentType, size, data, delFlag, userId) VALUES(#{fileName}, #{contentType}, #{size}, #{data}, #{delFlag}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(File file);

    @Update("UPDATE FILES SET delFlag = 1 WHERE id = #{id}")
    int delete(int id);
}
