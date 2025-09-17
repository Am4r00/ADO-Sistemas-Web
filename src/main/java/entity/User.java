package entity;

import org.hibernate.validator.constraints.UUID;

public class User {

    @UUID
    private String id;
    private String nome;
    private String cpf;

    public User() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    public User(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.id = java.util.UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
