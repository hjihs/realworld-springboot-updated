package org.example.springrealworld.exception;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Generic error model for handling exceptions
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName("errors")
public class GenericErrorModel {
    private List<String> body;

    public static GenericErrorModel from(String message) {
        return new GenericErrorModel(List.of(message));
    }

    public static ResponseEntity<?> handleExceptions(String message) {
        return ResponseEntity.unprocessableEntity().body(
                GenericErrorModel.from(message)
        );
    }

}