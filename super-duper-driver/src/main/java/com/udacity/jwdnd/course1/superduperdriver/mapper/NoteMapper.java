package com.udacity.jwdnd.course1.superduperdriver.mapper;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE delFlag = 0")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE id = #{id} AND delFlag = 0")
    Note getNoteDetail(Integer id);

    @Insert("INSERT INTO NOTES (title, description, delFlag, userId) VALUES(#{title}, #{description}, #{delFlag}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Note note);

    @Update("UPDATE NOTES SET title = #{title}, description = #{description} WHERE id = #{id}")
    int update(Note note);

    @Update("UPDATE NOTES SET delFlag = 1 WHERE id = #{id}")
    int delete(int id);
}
