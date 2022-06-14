package com.nikitax.simpleREST.controller;


import com.nikitax.simpleREST.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private int counter = 4;

    public List<Map<String, String>> messages = new ArrayList<>(){
        {
            add(new HashMap<>(){{put("id", "1"); put("text", "message1");}});
            add(new HashMap<>(){{put("id", "2"); put("text", "message2");}});
            add(new HashMap<>(){{put("id", "3"); put("text", "message3");}});
        }
    };

    private Map<String, String> getMessage(@PathVariable String id){
        return messages.stream()
                .filter(messages->messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping()
    public List<Map<String,String>> list(){
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message){
        messages.add(message);

        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message){
        Map<String, String> messageFromDB = getMessage(id);
        messageFromDB.putAll(message);
        messageFromDB.put("id", id);
        return messageFromDB;
    }

    @DeleteMapping("{id}")
    public void delete (@PathVariable String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }

}
