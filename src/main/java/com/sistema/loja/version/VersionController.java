package com.sistema.loja.version;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class VersionController {

    @Value("${app.version}")
    private String version;

    @Value("${app.environment}")
    private String environment;

    @GetMapping("/version")
    public Map<String, String> getVersion() {
        Map<String, String> info = new HashMap<>();
        info.put("version", version);
        info.put("environment", environment);
        info.put("buildDate", LocalDate.now().toString());
        return info;
    }
}