package com.udacity.jwdnd.course1.superduperdriver.model.mapper;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.FileEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE delFlag = 0")
    List<FileEntity> getAllFiles();

    @Select("SELECT * FROM FILES WHERE id = #{id} AND delFlag = 0")
    FileEntity getFileDetail(int id);

    @Insert("INSERT INTO FILES (fileName, contentType, size, data, delFlag, userId) VALUES(#{fileName}, #{contentType}, #{size}, #{data}, #{delFlag}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(FileEntity fileEntity);

    @Update("UPDATE FILES SET delFlag = 1 WHERE id = #{id}")
    int delete(int id);
}
