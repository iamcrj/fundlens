package com.iamcrj.fundlens.controller;

import com.iamcrj.fundlens.service.NseIpoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nse/ipo")
public class NseIpoController {
        private final NseIpoService nseIpoService;

        public NseIpoController(NseIpoService nseIpoService) {
            this.nseIpoService = nseIpoService;
        }

        @GetMapping("/current")
        public ResponseEntity<String> getCurrentIpos() {
            return nseIpoService.fetchCurrentIpos();
        }
}
