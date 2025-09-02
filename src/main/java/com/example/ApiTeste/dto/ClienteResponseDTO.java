package com.example.ApiTeste.dto;

import com.example.ApiTeste.model.Cliente;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNascimento;
    private Integer idade;
    private String telefone;
    private EnderecoDTO endereco;
    
    @Data
    public static class EnderecoDTO {
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String estado;
        private String cep;
    }
    
    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setCpf(cliente.getCpf());
        dto.setDataNascimento(cliente.getDataNascimento());
        dto.setIdade(cliente.getIdade());
        dto.setTelefone(cliente.getTelefone());
        
        if (cliente.getEndereco() != null) {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setLogradouro(cliente.getEndereco().getLogradouro());
            enderecoDTO.setNumero(cliente.getEndereco().getNumero());
            enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());
            enderecoDTO.setBairro(cliente.getEndereco().getBairro());
            enderecoDTO.setCidade(cliente.getEndereco().getCidade());
            enderecoDTO.setEstado(cliente.getEndereco().getEstado());
            enderecoDTO.setCep(cliente.getEndereco().getCep());
            dto.setEndereco(enderecoDTO);
        }
        
        return dto;
    }
}