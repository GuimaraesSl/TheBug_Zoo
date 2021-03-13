package com.example.thebug_zoo.entity;

public class Species {

    public int _id;
    public int id;
    public int armario;
    public int estante;
    public String ordem;
    public String familia;
    public String identificacao;
    public String inf_adicionais;
    public String fonte;
    public String coletor;
    public String _local;
    public String _data;

    public Species(){

    }

    public Species(int _id, int id, int armario, int estante, String ordem, String familia, String identificacao, String inf_adicionais, String fonte, String coletor, String _local, String _data){

        this._id = _id;
        this.id = id;
        this.armario = armario;
        this.estante = estante;
        this.ordem = ordem;
        this.familia = familia;
        this.identificacao = identificacao;
        this.inf_adicionais = inf_adicionais;
        this.fonte = fonte;
        this._local = _local;
        this._data = _data;

    }

}
