package com.example.demo;

import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
	
	private final NoteRepository noteRepository;
	
	public NoteController(NoteRepository repository) {
		noteRepository = repository;
	}
	
	//READ
	@GetMapping("/notes")
	Iterable<Note> getNotes(){
		return noteRepository.findAll();
	}
	
	@GetMapping("/notes/{noteId}")
	Optional<Note> getNotes(@PathVariable Long noteId)
	{
//		Optional<Note> opt = noteRepository.findById(noteId);
//		
//		Note n = opt.orElseThrow();
		return noteRepository.findById(noteId);
		
	}
	
	
	//CREATE
	@PostMapping("/notes")
	Note createNote(@RequestBody Note newNote)
	{
		return noteRepository.save(newNote);
	}
	
	
	//UPDATE
	@PutMapping("notes/{noteId}")
	Note updateNote(@PathVariable Long noteId, @RequestBody Note noteDto)
	{
		//provare anche con orelsethrow
		Note n = noteRepository.findById(noteId).get();
		
		n.setTitle(noteDto.getTitle());
		n.setContent(noteDto.getContent());
		
		return noteRepository.save(n);
	}
	
	
	//DELETE
	@DeleteMapping("notesDelete/{noteId}")
	String deleteNote(@PathVariable Long noteId) {
		Note n = noteRepository.findById(noteId).get();
		
		noteRepository.delete(n);
		
		return "eliminazione avvenuta";
	}
	
	@GetMapping("/form")
	public String getFormPage() {
		return "form"; // Assuming "form" is the name of your HTML file
	}
	
	
	

}
