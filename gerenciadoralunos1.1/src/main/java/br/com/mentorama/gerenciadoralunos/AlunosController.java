package br.com.mentorama.gerenciadoralunos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gerenciadoralunos")
public class AlunosController {

    @Autowired
    private AlunosService alunosService;

    //listar todos os alunos podendo filtrar por nome ou idade
    @GetMapping
    public List<Aluno> findAll(@RequestParam(required = false) String nome, @RequestParam(required = false) Integer idade){
        return alunosService.findAll(nome,idade);
    }

    //buscar aluno por id
    @GetMapping("/{id}")
    public Aluno findById(@PathVariable("id") Integer id){
        return alunosService.findById(id);
    }

    //cadastrar novo aluno
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Aluno aluno){
        return alunosService.add(aluno);
    }

    //editar cadastro
    @PutMapping
    public ResponseEntity update(@RequestBody Aluno aluno){
        return alunosService.update(aluno);
    }

    //remover registro de aluno
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        return alunosService.delete(id);
    }
}
