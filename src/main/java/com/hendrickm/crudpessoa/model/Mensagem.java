/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hendrickm.crudpessoa.model;

/**
 *
 * @author Matheus
 */
public class Mensagem{
        String resultado;

        public Mensagem(String text) {
            this.resultado = text;
        }

        public void setText(String text) {
            this.resultado = text;
        }
        
        
    }