/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendrickm.crudpessoa.model;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

/**
 *
 * @author Matheus
 */
public class PessoaDAO {

    private Session session;
    private SessionFactory sessionFactory;

    public PessoaDAO() {
    }

    private SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        sessionFactory
                = configuration.buildSessionFactory(standardRegistry);

        return sessionFactory;
    }

    public void cadastrar(Pessoa pessoa) {
        session = buildSessionFactory().openSession();
        Transaction trans = null;
        try {
            trans = session.beginTransaction();
            session.save(pessoa);
            System.out.println(pessoa.getDataNascimento());
            session.flush();
            trans.commit();
        } catch (RuntimeException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Pessoa> listar() {
        session = buildSessionFactory().openSession();
        List<Pessoa> resultado =  session.createQuery("FROM Pessoa").list();
        return resultado;
    }

    public List<Pessoa> buscar(String nome, String cpf) {
        session = buildSessionFactory().openSession();
        Query query = session.createQuery("FROM Pessoa");
        
        if (nome!=null && !nome.isEmpty()) {
            query = session.createQuery("FROM Pessoa WHERE nome like :nome");
            query.setParameter("nome", "%" + nome + "%");
            if(cpf != null &&  !cpf.isEmpty()){
                System.out.println("ENTREI");
                query = session.createQuery("FROM Pessoa WHERE nome like :nome AND cpf = :cpf");
                query.setParameter("nome", "%" + nome + "%");
                query.setParameter("cpf", cpf);
            }
        }
        
        else if (cpf != null &&  !cpf.isEmpty()) {
            query = session.createQuery("FROM Pessoa WHERE cpf like :cpf");
            query.setParameter("cpf", cpf);
        }
        
        List resultado = query.list();
        
        return resultado;
    }
    
    public Pessoa buscarPorId(long id){
        session = buildSessionFactory().openSession();
        Query query = session.createQuery("FROM Pessoa WHERE id = :id ");
        query.setParameter("id", id);
        List resultado = query.list();
        
        return (Pessoa)resultado.get(0);
    }
    
    public void atualizar(Pessoa pessoa) {
        session = buildSessionFactory().openSession();
        Transaction trans = null;
        try {
            trans = session.beginTransaction();
            session.saveOrUpdate(pessoa);
            session.flush();
            trans.commit();
        } catch (RuntimeException ex) {
            if (trans != null) {
                trans.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deletar(Long id) {
        Transaction trans = null;
        session = buildSessionFactory().openSession();

        try {
            trans = session.beginTransaction();
            Pessoa pessoa = session.load(Pessoa.class, id);

            if (pessoa != null) {
                session.delete(pessoa);
                session.flush();
                trans.commit();
            }
        } catch (RuntimeException e) {
            if (trans != null) {
                trans.rollback();
            }
            e.printStackTrace();
        } finally {

            session.close();
        }

    }

    

}
