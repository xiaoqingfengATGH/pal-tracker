package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.entity.TimeEntry;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> local = new ConcurrentHashMap<Long, TimeEntry>();
    private AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = idGenerator.incrementAndGet();
        this.local.put(id, timeEntry);
        timeEntry.setId(id);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        return local.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>
                (local.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if(local.containsKey(id)) {
            timeEntry.setId(id);
            local.replace(id, timeEntry);
            return timeEntry;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        local.remove(id);
    }
}