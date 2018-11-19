/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Gabriel Strack
 */
@Document
@CompoundIndexes({
    @CompoundIndex(
            name = "anoMes_idx",
            def = "{'ano': 1,'mes': 1}", unique = true)

})
public class Mes {

    @Id
    private String id;
    private String mes;
    private String ano;
    private int numeroNotas;
    private Double valorTotal;
    private Double credito;

    public Mes(String mes, String ano, int numeroNotas, Double valorTotal, Double credito) {
        this.mes = mes;
        this.ano = ano;
        this.numeroNotas = numeroNotas;
        this.valorTotal = valorTotal;
        this.credito = credito;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public int getNumeroNotas() {
        return numeroNotas;
    }

    public void setNumeroNotas(int numeroNotas) {
        this.numeroNotas = numeroNotas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Double getPercCredito() {
        return (credito / valorTotal) * 100;
    }

    public Double getMediaPorNota() {
        return credito / numeroNotas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mes other = (Mes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mes + "/" + ano;
    }

}
