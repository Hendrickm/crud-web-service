/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendrickm.crudpessoa.services;

import com.hendrickm.crudpessoa.model.Pessoa;
import com.hendrickm.crudpessoa.model.PessoaDAO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author Matheus
 */
@Path("pessoa")
public class PessoaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PessoaResource
     */
    public PessoaResource() {
    }

    @POST
    @Consumes("application/json")
    public Response cadastrar(String data) {
        Gson gson = new Gson();
        
        Pessoa pessoa = gson.fromJson(data, Pessoa.class);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.cadastrar(pessoa);
        
        Mensagem msg = new Mensagem("Cadastro realizado");
        String json =gson.toJson(msg);
        
        return Response
                .status(200)
                .entity(json)
                .build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listar() {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> pessoas = pessoaDAO.listar();
        Gson gson = new Gson();
        String data = gson.toJson(pessoas);     
        return Response
            .status(200)
            .entity(data)
            .build();
    }
    
    @GET 
    @Produces("application/json") 
    @Path("/{id:[0-9][0-9]*}")
    public String buscarPorId(@PathParam("id") long id) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        
        Gson gson = new Gson();
        String data = gson.toJson(pessoa);
        
        return data;
    }
    
    
    @GET 
    @Produces("application/json") 
    @Path("/{nome}/{cpf}") 
    public String buscar(@PathParam("nome") String nome, @PathParam("cpf") String cpf) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        List<Pessoa> pessoas = pessoaDAO.buscar(nome, cpf);
        
        Gson gson = new Gson();
        String data = gson.toJson(pessoas);
        
        return data;
    }
    
    @PUT
    @Consumes("application/json")
    public Response atualizar(String data) {
        Gson gson = new Gson();
        Pessoa pessoa = gson.fromJson(data, Pessoa.class);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.atualizar(pessoa);
        return Response.status(200).entity("Cadastro atualizado.").build();
    }
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    public Response deletar(@PathParam("id") long id) {
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.deletar(id);
        
        
        Gson gson = new Gson();
        Mensagem msg = new Mensagem("Cadastro realizado");
        String json = gson.toJson(msg);
                
        
        
        return Response.status(200).entity(json).build();
    }
    
    public class Mensagem{
        String resultado;

        public Mensagem(String text) {
            this.resultado = text;
        }

        public void setText(String text) {
            this.resultado = text;
        }
        
        
    }
    
}


    