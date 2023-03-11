package com.assemblychallenger.models.entidades;

import javax.persistence.*;


@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pessoa")
public class Pessoa {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private  String nome;
        private String logradouro;
        private String bairro;
        private String numero;
        private Long cep;
        private String cidade;
        private String UF;




}
