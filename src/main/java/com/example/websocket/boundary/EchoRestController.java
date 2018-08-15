package com.example.websocket.boundary;

import com.example.websocket.entity.Echo;
import com.example.websocket.entity.EchoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/echo")
@RestController
public class EchoRestController {

    private final EchoRepository echoRepository;

    public EchoRestController(EchoRepository echoRepository) {
        this.echoRepository = echoRepository;
    }

    @GetMapping
    public List<Echo> get() {
        return echoRepository.findAll();
    }
}
