package main.java.com.example.thymeleaf.sprithyme.controller;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class IndexController {
    public String index() {
        return "redirect:/students";
    }
}
