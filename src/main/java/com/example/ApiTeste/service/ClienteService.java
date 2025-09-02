package com.example.ApiTeste.service;

import com.example.ApiTeste.dto.ClienteRequestDTO;
import com.example.ApiTeste.dto.ClienteResponseDTO;
import com.example.ApiTeste.execeptions.ClienteExistenteException;
import com.example.ApiTeste.execeptions.ClienteNaoEncontradoException;
import com.example.ApiTeste.model.Cliente;
import com.example.ApiTeste.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        validarClienteExistente(dto.getCpf(), dto.getEmail());
        
        Cliente cliente = new Cliente();
        mapperDtoParaEntity(dto, cliente);
        
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ClienteResponseDTO.fromEntity(clienteSalvo);
    }
    
    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> listarClientes(Pageable pageable) {
        return clienteRepository.findAll(pageable)
                .map(ClienteResponseDTO::fromEntity);
    }
    
    @Transactional(readOnly = true)
    public Page<ClienteResponseDTO> buscarClientes(String termo, Pageable pageable) {
        return clienteRepository.search(termo, pageable)
                .map(ClienteResponseDTO::fromEntity);
    }
    
    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
        return ClienteResponseDTO.fromEntity(cliente);
    }
    
    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(id));
        
        // Verifica se CPF/Email já existem em outros clientes
        if (!cliente.getCpf().equals(dto.getCpf()) && 
            clienteRepository.existsByCpf(dto.getCpf())) {
            throw new ClienteExistenteException("CPF", dto.getCpf());
        }
        
        if (!cliente.getEmail().equals(dto.getEmail()) && 
            clienteRepository.existsByEmail(dto.getEmail())) {
            throw new ClienteExistenteException("Email", dto.getEmail());
        }
        
        mapperDtoParaEntity(dto, cliente);
        Cliente clienteAtualizado = clienteRepository.save(cliente);
        return ClienteResponseDTO.fromEntity(clienteAtualizado);
    }
    
    @Transactional
    public void excluirCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException(id);
        }
        clienteRepository.deleteById(id);
    }
    
    private void validarClienteExistente(String cpf, String email) {
        if (clienteRepository.existsByCpf(cpf)) {
            throw new ClienteExistenteException("CPF", cpf);
        }
        if (clienteRepository.existsByEmail(email)) {
            throw new ClienteExistenteException("Email", email);
        }
    }
    
    private void mapperDtoParaEntity(ClienteRequestDTO dto, Cliente cliente) {
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setDataNascimento(dto.getDataNascimento());
        cliente.setTelefone(dto.getTelefone());
        
        if (dto.getEndereco() != null) {
            Cliente.Endereco endereco = new Cliente.Endereco();
            endereco.setLogradouro(dto.getEndereco().getLogradouro());
            endereco.setNumero(dto.getEndereco().getNumero());
            endereco.setComplemento(dto.getEndereco().getComplemento());
            endereco.setBairro(dto.getEndereco().getBairro());
            endereco.setCidade(dto.getEndereco().getCidade());
            endereco.setEstado(dto.getEndereco().getEstado());
            endereco.setCep(dto.getEndereco().getCep());
            cliente.setEndereco(endereco);
        }
    }
}