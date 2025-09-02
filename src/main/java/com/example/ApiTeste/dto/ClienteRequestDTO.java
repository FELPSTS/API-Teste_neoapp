package com.example.ApiTeste.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100)
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;
    
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}")
    private String cpf;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    @Past
    private LocalDate dataNascimento;
    
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;
    
    @Valid
    private EnderecoDTO endereco;
    
    @Data
    public static class EnderecoDTO {
        @NotBlank
        private String logradouro;
        
        @NotBlank
        private String numero;
        
        private String complemento;
        
        @NotBlank
        private String bairro;
        
        @NotBlank
        private String cidade;
        
        @NotBlank
        @Size(min = 2, max = 2)
        private String estado;
        
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        private String cep;
    }
}