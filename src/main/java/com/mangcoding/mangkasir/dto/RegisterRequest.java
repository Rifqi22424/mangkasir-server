package com.mangcoding.mangkasir.dto;

// import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Pattern;
// import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username is mandatory")
    private String username;
    
    @NotBlank(message = "Username is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email should be valid and must end with @gmail.com")
    // @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String gmail;

    @NotBlank(message = "Password cannot be empty")
    // @Size(min = 1, message = "Password cannot be empty")
    private String password;
    
    @NotBlank(message = "Confirm Password cannot be empty")
    // @Size(min = 1, message = "Confirm Password cannot be empty")
    private String confirmPassword;
}