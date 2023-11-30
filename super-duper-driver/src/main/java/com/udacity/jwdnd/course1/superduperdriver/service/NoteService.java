package com.udacity.jwdnd.course1.superduperdriver.service;

import com.udacity.jwdnd.course1.superduperdriver.model.entities.Note;
import com.udacity.jwdnd.course1.superduperdriver.model.mapper.NoteMapper;
import com.udacity.jwdnd.course1.superduperdriver.util.ProjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final ProjectUtils projectUtils;

    public NoteService(NoteMapper noteMapper, final ProjectUtils projectUtils) {
        this.noteMapper = noteMapper;
        this.projectUtils = projectUtils;
    }

    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }

    public int addNote(Note note) {
        return noteMapper.insert(new Note(note.getTitle(), note.getDescription(), false, projectUtils.getCurrentUserId()));
    }

    public int updateNote(Note note) {
        Note currentData = noteMapper.getNoteDetail(note.getId());

        if (Objects.isNull(currentData)) {
            return 0;
        }

        if (StringUtils.hasText(note.getTitle()) && !note.getTitle().equals(currentData.getTitle())) {
            currentData.setTitle(note.getTitle());
        }

        if (StringUtils.hasText(note.getDescription()) && !note.getDescription().equals(currentData.getDescription())) {
            currentData.setDescription(note.getDescription());
        }

        return noteMapper.update(currentData);
    }

    public int deleteNote(Integer id) {
        return noteMapper.delete(id);
    }
}
