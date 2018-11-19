/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;

/**
 *
 * @author Elizelton Lisboa
 */
public class InstituicaoLinha {

    private String cnpjInst;
    private String mes;
    private String ano;

    public InstituicaoLinha(String linha) throws IOException {
        String[] partes = linha.split(" - ");   // Dividir a string em partes. 
        partes[1] = partes[1].replaceAll("CNPJ:", "");
        this.cnpjInst = partes[1].replaceAll("[\\\\.\\\\/\\\\-]", "").trim();
//
        partes[2] = partes[2].replaceAll("REF.:", "");
        partes[2] = partes[2].replaceAll("[\\\\;]", "").trim();
        String[] partMesAno = partes[2].split("/");
        this.mes = partMesAno[0];
        this.ano = partMesAno[1];

    }

    public String getMes() {
        return mes;
    }

    public String getCnpjInst() {
        return cnpjInst;
    }

    public String getAno() {
        return ano;
    }
}
