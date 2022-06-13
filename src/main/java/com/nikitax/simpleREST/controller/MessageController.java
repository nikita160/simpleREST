package com.nikitax.simpleREST.controller;


import com.nikitax.simpleREST.exception.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    public List<Map<String, String>> messages = new ArrayList<>(){
        {
            add(new HashMap<>(){{put("id", "1"); put("text", "message1");}});
            add(new HashMap<>(){{put("id", "2"); put("text", "message2");}});
            add(new HashMap<>(){{put("id", "3"); put("text", "message3");}});
        }
    };

    @GetMapping()
    public List<Map<String,String>> list(){
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id){
        return messages.stream()
                .filter(messages->messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
