package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/my-time-entries")
public class MyTimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public MyTimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry today) {
        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(today),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long l) {
        TimeEntry te = timeEntryRepository.find(l);
        return new ResponseEntity<TimeEntry>(te,(te!=null )?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long l, @RequestBody TimeEntry expected) {
        TimeEntry updatedEntry = timeEntryRepository.update(l, expected);
        return new ResponseEntity(updatedEntry,updatedEntry!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long l) {
        timeEntryRepository.delete(l);
        ResponseEntity<TimeEntry> re = new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
        return re;
    }
}
