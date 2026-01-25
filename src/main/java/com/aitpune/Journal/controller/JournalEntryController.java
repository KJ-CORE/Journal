package com.aitpune.Journal.controller;

import com.aitpune.Journal.entity.JournalEntry;
import com.aitpune.Journal.entity.User;
import com.aitpune.Journal.service.JournalEntryService;
import com.aitpune.Journal.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<JournalEntry> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        return all;
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.findByUsername(username);

            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{ID}")
    public ResponseEntity<JournalEntry> getJournalEntryID(@PathVariable ObjectId ID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.equals(ID)).toList();
        Optional<JournalEntry> journalEntry = null;
        if (!collect.isEmpty()) {
            journalEntry = journalEntryService.getbyID(ID);
            if (journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(journalEntry.get(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{ID}")
    public JournalEntry deleteJournalEntryID(@PathVariable ObjectId ID) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        journalEntryService.deletebyID(ID,username);
        return null;
    }

    @PutMapping("id/{username}/{ID}")
    public ResponseEntity<?> updateJournalEntryID(@PathVariable ObjectId ID,
                                             @RequestBody JournalEntry newEntry,
                                             @PathVariable String username) {
        JournalEntry old = journalEntryService.getbyID(ID).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle()!=null && newEntry.getTitle().isEmpty()? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && newEntry.getContent().isEmpty() ? newEntry.getTitle(): old.getContent());
            journalEntryService.saveEntry(old);

        return new ResponseEntity(old,HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
