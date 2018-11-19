/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static Config.DAO.empresaRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.HTTP;

/**
 *
 * @author Gabriel Strack
 */
@Document
@CompoundIndexes({
    @CompoundIndex(
            name = "anoMesEmpresa_idx",
            def = "{'ano': 1,'mes': 1, 'empresa' : 1}", unique = true)

})
public class MesEmpresa {

    @Id
    private String id;
    @DBRef
    private Empresa empresa;
    private String mes;
    private String ano;
    private int numeroNotas;
    private Double valorTotal;
    private Double credito;

    public MesEmpresa(Empresa empresa, String mes, String ano, int numeroNotas, Double valorTotal, Double credito) {
        this.empresa = empresa;
        this.mes = mes;
        this.ano = ano;
        this.numeroNotas = numeroNotas;
        this.valorTotal = valorTotal;
        this.credito = credito;
    }

    public void somaNota(Double valorTotal, Double credito) {
        this.numeroNotas += 1;
        this.valorTotal += valorTotal;
        this.credito += credito;
    }

    public String getNomeFantasia() {
        return empresa.getNomeFantasia();
    }

    public String getCidadeSigla() {
        if (empresa.getCidade() != null) {
            return empresa.getCidade().getSigla();
        } else {
            return "";
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final MesEmpresa other = (MesEmpresa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private JSONObject readJsonFromUrl(String url) throws MalformedURLException, IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    @Override
    public String toString() {
        return "MesEmpresa{" + "empresa=" + empresa + ", mes=" + mes + ", ano=" + ano + ", numeroNotas=" + numeroNotas + ", valorTotal=" + valorTotal + ", credito=" + credito + '}';
    }

}
