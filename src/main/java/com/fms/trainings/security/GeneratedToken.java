package com.fms.trainings.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component("generatedToken")
@Data @AllArgsConstructor @NoArgsConstructor
public class GeneratedToken {
    private String accessToken;
}
