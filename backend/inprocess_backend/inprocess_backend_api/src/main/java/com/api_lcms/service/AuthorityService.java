package com.api_lcms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api_lcms.dto.AuthorityDTO;
import com.api_lcms.model.Authority;
import com.api_lcms.model.Employee;
import com.api_lcms.repository.AuthorityRepository;
import com.api_lcms.repository.EmployeeRepository;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Authority> assignAuthorityToEmployee(Integer emp_id, List<AuthorityDTO> authorityDTOs) {
        Employee employee = employeeRepository.findById(emp_id)
                .orElseThrow(() -> new RuntimeException("Employee Id Not Found"));
        
        List<Authority> authorities = authorityDTOs.stream().map(dto -> {
            Authority authority = new Authority();
            authority.setEmployee(employee);
            authority.setDescription(dto.getDescription());
            authority.setAllowView(dto.isAllowView());
            authority.setAllowCreate(dto.isAllowCreate());
            authority.setAllowUpdate(dto.isAllowUpdate());
            return authorityRepository.save(authority);
        }).toList();
        
        return authorities;
    }

    public Authority revokeAuthorityFromEmployee(Integer emp_id, Integer auth_id) {
        Optional<Authority> authorityOpt = authorityRepository.findById(auth_id);

        if (authorityOpt.isPresent()) {
            Authority authority = authorityOpt.get();
            if (authority.getEmployee() != null && authority.getEmployee().getEmp_id().equals(emp_id)) {
                authority.setEmployee(null);
//                authority.setStatus("revoked"); // Set status to indicate revocation
                authorityRepository.save(authority);
            }
        }
        return null;
    }
}
