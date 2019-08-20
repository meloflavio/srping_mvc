package com.flaviomelo.mvc.web.controller;

import com.flaviomelo.mvc.domain.BlockchainTransaction;
import com.flaviomelo.mvc.service.BlockchainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class BlockchainController {
    private final BlockchainService service;

    public BlockchainController(BlockchainService service) {
        this.service = service;
    }

    @PostMapping("/transaction")
    public BlockchainTransaction execute(@RequestBody BlockchainTransaction transaction) throws IOException {
        return service.process(transaction);

    }
}
