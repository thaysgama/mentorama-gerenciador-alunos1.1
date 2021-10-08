package br.com.mentorama.gerenciadoralunos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunosService {

    public final List<Aluno> alunos;

    public AlunosService() {
        this.alunos = new ArrayList<>();
    }

    //listar todos os alunos podendo filtrar por nome ou idade
    public List<Aluno> findAll(String nome, Integer idade){
        List<Aluno> alunosFiltrados = new ArrayList();
        if(nome != null && idade !=null){
            alunosFiltrados = alunos.stream()
                    .filter(aluno -> aluno.getNome().contains(nome) && aluno.getIdade().equals(idade))
                    .collect(Collectors.toList());
        } else if (nome!=null){
            alunosFiltrados = alunos.stream()
                    .filter(aluno -> aluno.getNome().contains(nome))
                    .collect(Collectors.toList());
        } else if (idade!=null){
            alunosFiltrados = alunos.stream()
                    .filter(aluno -> aluno.getIdade().equals(idade))
                    .collect(Collectors.toList());
        } else
            alunosFiltrados = alunos;

        if(alunosFiltrados.isEmpty()){
            throw new AlunoInexistenteException();
        }

        return alunosFiltrados;
    }

    //buscar aluno por id
    public Aluno findById(Integer id){
        Aluno alunoEncontrado = this.alunos.stream()
                .filter(aluno -> aluno.getId().equals(id))
                .findFirst()
                .orElse(null);
        if(alunoEncontrado != null){
            return alunoEncontrado;
        }
        throw new AlunoInexistenteException();
    }

    //cadastrar novo aluno
    public ResponseEntity<Integer> add(Aluno aluno){
        if(aluno.getId() == null){
            aluno.setId(alunos.size()+1);
        }
        alunos.add(aluno);
        return new ResponseEntity<>(aluno.getId(), HttpStatus.CREATED);
    }

    //editar cadastro
    public ResponseEntity update(Aluno aluno){
        alunos.stream()
                .filter(estudante -> estudante.getId().equals(aluno.getId()))
                .forEach(estudante -> {
                    estudante.setIdade(aluno.getIdade());
                    estudante.setNome(aluno.getNome());}
                );
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    //remover registro de aluno
    public ResponseEntity delete(Integer id){
        alunos.removeIf(aluno -> aluno.getId().equals(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
