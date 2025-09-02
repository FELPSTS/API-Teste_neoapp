package com.example.ApiTeste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    
    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    @Column(nullable = false)
    private LocalDate dataNascimento;
    
    @NotBlank(message = "Telefone é obrigatório")
    @Column(nullable = false, length = 20)
    private String telefone;
    
    @Embedded
    private Endereco endereco;
    
    @Transient
    public Integer getIdade() {
        if (this.dataNascimento == null) {
            return null;
        }
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
    
    @Data
    @Embeddable
    public static class Endereco {
        @NotBlank(message = "Logradouro é obrigatório")
        @Column(length = 100)
        private String logradouro;
        
        @NotBlank(message = "Número é obrigatório")
        @Column(length = 10)
        private String numero;
        
        @Column(length = 50)
        private String complemento;
        
        @NotBlank(message = "Bairro é obrigatório")
        @Column(length = 50)
        private String bairro;
        
        @NotBlank(message = "Cidade é obrigatória")
        @Column(length = 50)
        private String cidade;
        
        @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres")
        @Column(length = 2)
        private String estado;
        
        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "CEP deve ter 8 dígitos")
        @Column(length = 8)
        private String cep;
    }
}