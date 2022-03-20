package com.joncabdev.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.joncabdev.noteapp.data.NotesDataSource
import com.joncabdev.noteapp.model.Note
import com.joncabdev.noteapp.ui.screens.NoteScreen
import com.joncabdev.noteapp.ui.screens.NoteViewModel
import com.joncabdev.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    val noteViewModel = viewModel<NoteViewModel>() sirve
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)



                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel){
    val notes = noteViewModel.noteList.collectAsState().value
    NoteScreen(notes = notes, onRemoveNote = {
        noteViewModel.deleteNote(it)
    }, onAddNote = {
        noteViewModel.addNote(it)
    })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppTheme {
        NoteScreen(notes = NotesDataSource().loadNotes(), onRemoveNote = {}, onAddNote = {})

    }
}