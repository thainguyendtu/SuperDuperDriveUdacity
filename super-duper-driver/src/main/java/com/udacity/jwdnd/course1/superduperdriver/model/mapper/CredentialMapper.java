package com.udacity.jwdnd.course1.superduperdriver.model.mapper;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.Credential;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE delFlag = 0")
    List<Credential> getAllCredentials();

    @Select("SELECT * FROM CREDENTIALS WHERE id = #{id} AND delFlag = 0")
    Credential getCredentialDetail(Integer id);

    @Insert("INSERT INTO CREDENTIALS (url, username, keyValue, password, delFlag, userId) VALUES (#{url}, #{username}, #{keyValue}, #{password}, #{delFlag}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Credential credential);

    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, password = #{password} WHERE id = #{id}")
    int update(Credential credential);

    @Update("UPDATE CREDENTIALS SET delFlag = 1 WHERE id = #{id}")
    int delete(int id);
}
