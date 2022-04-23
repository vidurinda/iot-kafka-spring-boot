package com.ices.iotvisualizer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document("user")
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AppUser {
    @Id
    private UUID id;

    @Indexed(unique=true)
    private String username;

    @JsonIgnore
    private String password;
}
