package com.api_lcms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_lcms.model.Authority;
import com.api_lcms.service.AuthorityService;
import com.api_lcms.dto.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @PostMapping("/assign")
    public List<Authority> assignAuthority(@RequestParam Integer emp_id, @RequestBody List<AuthorityDTO> authorityDTOs) {
        return authorityService.assignAuthorityToEmployee(emp_id, authorityDTOs);
    }

    @PostMapping("/revoke")
    public Authority revokeAuthority(@RequestParam Integer emp_id, @RequestParam Integer auth_id) {
        return authorityService.revokeAuthorityFromEmployee(emp_id, auth_id);
    }
}
