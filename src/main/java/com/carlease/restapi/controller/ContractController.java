package com.carlease.restapi.controller;

import com.carlease.restapi.dto.ContractDTO;
import com.carlease.restapi.service.ContractService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {

  @Autowired
  private ContractService contractService;

  @GetMapping
  public ResponseEntity<List<ContractDTO>> getAllContracts() {
    return ResponseEntity.ok(contractService.getAllContracts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContractDTO> getContractById(@PathVariable Long id) {
    ContractDTO contractDTO = contractService.getContractById(id);
    return ResponseEntity.ok(contractDTO);
  }

  @PostMapping
  public ResponseEntity<ContractDTO> createContract(@RequestBody ContractDTO contractDTO) {
    ContractDTO createdContract = contractService.createContract(contractDTO);
    return ResponseEntity.ok(createdContract);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ContractDTO> updateContract(@PathVariable Long id,
      @RequestBody ContractDTO contractDTO) {
    ContractDTO updatedContract = contractService.updateContract(id, contractDTO);
    return ResponseEntity.ok(updatedContract);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContract(@PathVariable Long id) {
    contractService.deleteContract(id);
    return ResponseEntity.noContent().build();
  }
}