/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendrickm.crudpessoa.services;

import com.hendrickm.crudpessoa.model.Pessoa;
import com.hendrickm.crudpessoa.model.PessoaDAO;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Web Service
 *
 * @author Matheus
*
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

    @RequestMapping(method = RequestMethod.POST)
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.atualizar(pessoa);
        return pessoa;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Pessoa> listar() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> pessoas = pessoaDAO.listar();
        return pessoas;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Pessoa buscarPorId(@PathVariable("id") Long id) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        return pessoa;
    }

    @RequestMapping("/query")
    public List<Pessoa> buscarPorQuery(@RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "cpf", defaultValue = "") String cpf) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> pessoas = pessoaDAO.buscar(nome, cpf);
        return pessoas;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Pessoa atualizar(@RequestBody Pessoa pessoa) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoaAtualizada = pessoaDAO.buscarPorId(pessoa.getId());
        pessoaDAO.atualizar(pessoa);
        return pessoaAtualizada;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Pessoa deletar(@PathVariable Long id) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoaDeletada = pessoaDAO.buscarPorId(id);
        pessoaDAO.deletar(id);
        return pessoaDeletada;
    }

}
