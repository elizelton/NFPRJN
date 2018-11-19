/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import model.*;
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
 * @author Elizelton Lisboa
 */
public class MesEmpresaLinha {

    @DBRef
    private Empresa empresa;
    private String mes;
    private String ano;
    private Double valor;
    private Double credito;

    public MesEmpresaLinha(String linha) {
        String[] partes = linha.split(";");   // Dividir a string em partes. 
        
        //FUNCAO DE TESTE 
        for (String parte : partes) {
            System.out.println(parte);
        }
        
//        ESSA PORRA PUTA QUE PARIL FILHA DA PUTA DO CARALHO NAO FUNCIONA NA TREAD 
//        if (partes[6].equals("CALCULADO")) {
        //Formatar CNPJ para apenas numeros
//            partes[0] = partes[0].replaceAll("[\\\\.\\\\/\\\\-]", "");
//            if (empresaRepository.findByCnpj(partes[0]) != null) {
//                this.empresa = empresaRepository.findByCnpj(partes[0]);
//            } else {
//                Empresa empTemp = new Empresa(partes[1], partes[1], partes[0], "Adicionada via Importação");
//                empresaRepository.insert(empTemp);
//                this.empresa = empresaRepository.findByCnpj(partes[0]);
//
//            }
//            String[] partMesAno = partes[3].split("/");
//            this.mes = partMesAno[1];
//            this.ano = partMesAno[2];
//
//            partes[4] = partes[4].replaceAll("R\\$ ", "");
//            partes[4] = partes[4].replaceAll("\\,", ".");
//            this.valor = Double.valueOf(partes[4]);
//
//            partes[5] = partes[5].replaceAll("R\\$ ", "");
//            partes[5] = partes[5].replaceAll("\\,", ".");
//            this.credito = Double.valueOf(partes[5]);
//        }

    }

    public String getMes() {
        return mes;
    }

    @Override
    public String toString() {
        return "MesEmpresaLinha{" + "empresa=" + empresa + ", mes=" + mes + ", ano=" + ano + ", valor=" + valor + ", credito=" + credito + '}';
    }

    public String getAno() {
        return ano;
    }

    public Double getValor() {
        return valor;
    }

    public Double getCredito() {
        return credito;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

}
