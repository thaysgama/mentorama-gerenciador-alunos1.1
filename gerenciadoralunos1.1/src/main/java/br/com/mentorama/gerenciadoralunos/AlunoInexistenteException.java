package br.com.mentorama.gerenciadoralunos;

public class AlunoInexistenteException extends RuntimeException{
    public AlunoInexistenteException() {
        super("Estudante não encontrado.");
    }
}
