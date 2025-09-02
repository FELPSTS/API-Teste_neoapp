package com.example.ApiTeste.controller;

import com.example.ApiTeste.dto.ClienteRequestDTO;
import com.example.ApiTeste.dto.ClienteResponseDTO;
import com.example.ApiTeste.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {
    
    private final ClienteService clienteService;
    
    @PostMapping
    @Operation(summary = "Criar novo cliente")
    public ResponseEntity<ClienteResponseDTO> criarCliente(
            @Valid @RequestBody ClienteRequestDTO dto) {
        ClienteResponseDTO cliente = clienteService.criarCliente(dto);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();
        
        return ResponseEntity.created(location).body(cliente);
    }
    
    @GetMapping
    @Operation(summary = "Listar clientes com paginação")
    public ResponseEntity<Page<ClienteResponseDTO>> listarClientes(
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(clienteService.listarClientes(pageable));
    }
    
    @GetMapping("/buscar")
    @Operation(summary = "Buscar clientes por termo")
    public ResponseEntity<Page<ClienteResponseDTO>> buscarClientes(
            @RequestParam String termo,
            @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(clienteService.buscarClientes(termo, pageable));
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente")
    public ResponseEntity<ClienteResponseDTO> atualizarCliente(
            @PathVariable Long id,
            @Valid @RequestBody ClienteRequestDTO dto) {
        return ResponseEntity.ok(clienteService.atualizarCliente(id, dto));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}