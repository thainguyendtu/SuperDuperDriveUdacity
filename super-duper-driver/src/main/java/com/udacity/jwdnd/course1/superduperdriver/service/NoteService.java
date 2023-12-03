package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.Note;
import com.udacity.jwdnd.course1.superduperdriver.mapper.NoteMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService {

    @Autowired
    NoteMapper noteMapper;

    @Autowired
    ProjectUtils projectUtils;

    @Autowired
    AuthenticationService authenticationService;

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes(authenticationService.getUserId());
    }

    public int addNote(Note note) {
        return noteMapper.insert(new Note(note.getNoteTitle(), note.getNoteDescription(), projectUtils.getCurrentUserId()));
    }

    public int updateNote(Note note) {
        Note currentData = noteMapper.getNoteDetail(note.getNoteId(), authenticationService.getUserId());

        if (Objects.isNull(currentData)) {
            return 0;
        }

        if (StringUtils.hasText(note.getNoteTitle()) && !note.getNoteTitle().equals(currentData.getNoteTitle())) {
            currentData.setNoteTitle(note.getNoteTitle());
        }

        if (StringUtils.hasText(note.getNoteDescription()) && !note.getNoteDescription().equals(currentData.getNoteDescription())) {
            currentData.setNoteDescription(note.getNoteDescription());
        }

        return noteMapper.update(currentData);
    }

    public int deleteNote(Integer id) {
        return noteMapper.delete(id);
    }
}
