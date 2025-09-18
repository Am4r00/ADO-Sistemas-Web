package com.meuapp.ado.dto;

import jakarta.validation.constraints.NotBlank;

public class SignUpUser {
    @NotBlank
    private String name;
    @NotBlank
    private String cpf;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getCpf() {
        return cpf;
    }

    public void setCpf(@NotBlank String cpf) {
        this.cpf = cpf;
    }
}
