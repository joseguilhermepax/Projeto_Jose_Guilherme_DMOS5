package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model;

import java.io.Serializable;

public class Pesquisa implements Serializable {

    private Integer id;
    private String uid;
    private String uf;
    private String state;
    private String cases;
    private String deaths;
    private String suspects;
    private String refuses;
    private String datetime;

    public Pesquisa ( String uid, String uf, String state, String cases, String deaths, String suspects, String refuses, String datetime) {

        this.uid      = uid;
        this.uf       = uf;
        this.state    = state;
        this.cases    = cases;
        this.deaths   = deaths;
        this.suspects = suspects;
        this.refuses  = refuses;
        this.datetime = datetime;
    }

    public Pesquisa (Integer id, String uid, String uf, String state, String cases, String deaths, String suspects, String refuses, String datetime) {

        this.id       = id;
        this.uid      = uid;
        this.uf       = uf;
        this.state    = state;
        this.cases    = cases;
        this.deaths   = deaths;
        this.suspects = suspects;
        this.refuses  = refuses;
        this.datetime = datetime;
    }

    public String getNomePesquisa() {

        String DataText = datetime.substring(0, 10);
        String HoraText = datetime.substring(11, 19);

        String[] DataSeparado = DataText.split("-");

        if (DataSeparado.length > 1) {
            return state + " " + DataSeparado[2] + "/" + DataSeparado[1] + "/" + DataSeparado[0] + " " + HoraText;
        }

        return state + " " + datetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCasos() {
        return cases;
    }

    public void setCasos(String casos) {
        this.cases = cases;
    }

    public String getMortes() {
        return deaths;
    }

    public void setMortes(String mortes) {
        this.deaths = deaths;
    }

    public String getSuspeitos() {
        return suspects;
    }

    public void setSuspeitos(String suspeitos) {
        this.suspects = suspeitos;
    }

    public String getRecusados() {
        return refuses;
    }

    public void setRecusados(String recusados) {
        this.refuses = recusados;
    }

    public String getData() {

        String DataText = datetime.substring(0, 10);
        String HoraText = datetime.substring(11, 19);

        String[] DataSeparado = DataText.split("-");

        if (DataSeparado.length > 1) {
            return DataSeparado[2] + "/" + DataSeparado[1] + "/" + DataSeparado[0] + " " + HoraText;
        }

        return datetime;
    }

    public void setData(String data) {
        this.datetime = datetime;
    }
}


