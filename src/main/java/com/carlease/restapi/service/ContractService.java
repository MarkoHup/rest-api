package com.carlease.restapi.service;

import com.carlease.restapi.dto.ContractDTO;
import com.carlease.restapi.model.Contract;
import com.carlease.restapi.repository.ContractRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

  private final ContractRepository contractRepository;

  @Autowired
  public ContractService(ContractRepository contractRepository) {
    this.contractRepository = contractRepository;
  }

  private ContractDTO mapToDTO(Contract contract) {
    return new ContractDTO(contract.getId(), contract.getCar(), contract.getCustomer(),
        contract.getStartDate(), contract.getEndDate(), contract.getMileage(),
        contract.getInterestRate());
  }

  private Contract mapToEntity(ContractDTO contractDTO) {
    return new Contract(contractDTO.getCar(), contractDTO.getCustomer(), contractDTO.getStartDate(),
        contractDTO.getEndDate(), contractDTO.getMileage(), contractDTO.getInterestRate());
  }

  public List<ContractDTO> getAllContracts() {
    return contractRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public ContractDTO getContractById(Long id) {
    Contract contract = contractRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contract not found"));
    return mapToDTO(contract);
  }

  public ContractDTO createContract(ContractDTO contractDTO) {
    Contract contract = mapToEntity(contractDTO);
    Contract savedContract = contractRepository.save(contract);
    return mapToDTO(savedContract);
  }

  public ContractDTO updateContract(Long id, ContractDTO contractDTO) {
    Contract existingContract = contractRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contract not found"));
    existingContract.setCar(contractDTO.getCar());
    existingContract.setCustomer(contractDTO.getCustomer());
    existingContract.setStartDate(contractDTO.getStartDate());
    existingContract.setEndDate(contractDTO.getEndDate());
    existingContract.setMileage(contractDTO.getMileage());
    existingContract.setInterestRate(contractDTO.getInterestRate());
    Contract updatedContract = contractRepository.save(existingContract);
    return mapToDTO(updatedContract);
  }

  public void deleteContract(Long id) {
    Contract contract = contractRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Contract not found"));
    contractRepository.delete(contract);
  }
}