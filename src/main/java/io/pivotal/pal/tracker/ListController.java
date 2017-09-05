package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ListController {
    @GetMapping("/listSomething")
    public String list()
    {
        return "ListSomething";
    }
}
