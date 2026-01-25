package com.aitpune.Journal.service;

import com.aitpune.Journal.entity.JournalEntry;
import com.aitpune.Journal.entity.User;
import com.aitpune.Journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){

        User user = userService.findByUsername(username);
        journalEntryRepository.save(journalEntry);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }
    @Transactional
    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();

    }
    public Optional<JournalEntry> getbyID(ObjectId myID){

        return journalEntryRepository.findById(myID);
    }
    public void deletebyID(ObjectId myID, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myID));
        userService.saveUser(user);
        journalEntryRepository.deleteById(myID);

    }

}
