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

    private byte[] lock = new byte[0];

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long id = idGenerator.incrementAndGet();
        this.local.put(id, timeEntry);
        timeEntry.setId(id);
        return timeEntry;
    }

    @Override
    public TimeEntry find(Long id) {
        synchronized (lock)
        {
            try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return local.get(id);}
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>
                (local.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        synchronized (lock)
        {
        local.replace(id, timeEntry);
        timeEntry.setId(id);
        return timeEntry;}
    }

    @Override
    public void delete(Long id) {
        local.remove(id);
    }
}