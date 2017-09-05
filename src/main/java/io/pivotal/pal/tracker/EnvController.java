package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private Map<String, String> evn;

    public EnvController(@Value("${PORT:NOT SET}")
                         String PORT,
                         @Value("${MEMORY_LIMIT:NOT SET}")
                         String MEMORY_LIMIT,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}")
                         String CF_INSTANCE_INDEX,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}")
                         String CF_INSTANCE_ADDR)
    {
        this.evn = new HashMap<String, String>();
        this.evn.put("PORT",PORT);
        this.evn.put("MEMORY_LIMIT",MEMORY_LIMIT);
        this.evn.put("CF_INSTANCE_INDEX",CF_INSTANCE_INDEX);
        this.evn.put("CF_INSTANCE_ADDR",CF_INSTANCE_ADDR);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv()
    {
        return this.evn;
    }
}
