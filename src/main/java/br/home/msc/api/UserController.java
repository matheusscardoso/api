package br.home.msc.api;

import br.home.msc.api.secutiry.entity.User;
import br.home.msc.api.secutiry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("status")
    public Map<String, String> getMethodName() {
        Map<String , String> result = new LinkedHashMap<>();
        result.put("api_status", "running" );
        result.put("date" , LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
        return result;
    }

    @GetMapping("users")
    public List<User> getMethodName1() {
        return repo.findAll();
    }



    @GetMapping("auth")
    public String getMethodName2() {
        return "autenticado";
    }

}
