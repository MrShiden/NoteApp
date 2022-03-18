package com.joncabdev.noteapp.ui.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.joncabdev.noteapp.data.NotesDataSource
import com.joncabdev.noteapp.model.Note

class NoteViewModel: ViewModel() {
    var noteList = mutableStateListOf<Note>()

    init {
//        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note: Note){
        noteList.add(note)
    }
    fun removeNote(note:Note){
        noteList.remove(note)
    }
    fun getAllNotes():List<Note>{
        return noteList
    }
}