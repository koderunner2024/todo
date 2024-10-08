package com.example.todo;

import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    private Configuration con;
    private List<String> todoList;

    @PostConstruct
    public void init() {
        todoList = new ArrayList<>();
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoList);
        return "index";
    }

    @PostMapping("/hello")
    public String hello(@RequestBody Map<String,Object> body, Model model) {
        model.addAttribute("name", body.get("name"));
        return "hello";
    }

    @PostMapping("/template")
    public String template(@RequestBody Map<String,String> templates) throws IOException {
        StringTemplateLoader stringLoader = new StringTemplateLoader();
        for(String templateKey : templates.keySet()){
            stringLoader.putTemplate(templateKey, templates.get(templateKey));
        }
        con.setTemplateLoader(new MultiTemplateLoader(new TemplateLoader[]{stringLoader, con.getTemplateLoader()}));
        return "index";
    }

    @PostMapping("/todo/add")
    public String addTodo(@RequestBody Map<String, String> request, Model model) {
        String todoItem = request.get("item");
        if (todoItem != null && !todoItem.isEmpty()) {
            todoList.add(todoItem);
        }
        model.addAttribute("todos", todoList);
        return "todo";
    }

    @PostMapping("/todo/delete")
    public String deleteTodo(@RequestBody Map<String, String> request, Model model) {
        String todoItem = request.get("item");
        todoList.remove(todoItem);
        model.addAttribute("todos", todoList);
        return "todo";
    }
}
