package com.joncabdev.noteapp.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joncabdev.noteapp.data.NotesDataSource
import com.joncabdev.noteapp.model.Note
import com.joncabdev.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
//    var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{
                    if (it.isNullOrEmpty()){
                        Log.d("Empty", ":Empty list ")
                    }else{
                        _noteList.value = it
                    }
                }
        }
//        noteList.addAll(NotesDataSource().loadNotes())
    }

     fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
     fun updateNote(note:Note) = viewModelScope.launch { repository.updateNote(note) }
     fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }

}