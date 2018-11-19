package utility;

import static Config.DAO.empresaRepository;
import static Config.DAO.instituicaoRepository;
import static Config.DAO.mesRepository;
import static Config.DAO.mesEmpresaRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import model.Empresa;
import model.Mes;
import model.MesEmpresa;

public class Dados {

    private BufferedReader br = null;
    private String nomeArq;
    private List<MesEmpresa> mesEmpresas = new ArrayList<>();
    private Mes mes;
    private List<Empresa> empresas = new ArrayList<>();
    private int totalNotas;
    private double totalValorNotas;
    private double totalCredito;
    private InstituicaoLinha insL;

    public Dados(String nomeArq) {
        this.nomeArq = nomeArq;
    }

    public boolean validaImportacao() {

        String linha;

        try {
            br = new BufferedReader(new FileReader(nomeArq));
            linha = br.readLine();
            insL = new InstituicaoLinha(linha);
            return instituicaoRepository.findByCnpj(insL.getCnpjInst()) != null;
        } catch (IOException e) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }
        return false;
    }

//    public void ler(int prog, int total) {
//        String linha;
//        try {
//            br = new BufferedReader(new FileReader(nomeArq));
//            total = br.lines().count();
////            br.skip(3);
//            while ((linha = br.readLine()) != null) {
//                System.out.println(linha);
////                somaNotas(new MesEmpresaLinha(linha));
//            }
//
//        } catch (Exception e) {
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//            } catch (IOException ex) {
//            }
//        }
//    }

    public String getNopublic {
        return nomeArq;
    }

    public List<MesEmpresa> getMesEmpresas() {
        return mesEmpresas;
    }

    public Mes getMes() {
        return new Mes(insL.getMes(), insL.getAno(), totalNotas, totalValorNotas, totalCredito);
    }

    public void somaNotas(MesEmpresaLinha mesEmpLinha) {
        if (mesEmpLinha.getEmpresa() != null) {
            int elemList = empresas.indexOf(mesEmpLinha.getEmpresa());
            totalNotas++;
            totalCredito += mesEmpLinha.getCredito();
            totalValorNotas += mesEmpLinha.getValor();
            if (elemList > -1) {
                mesEmpresas.get(elemList).somaNota(mesEmpLinha.getValor(), mesEmpLinha.getCredito());
            } else {
                mesEmpresas.add(new MesEmpresa(mesEmpLinha.getEmpresa(), mesEmpLinha.getMes(), mesEmpLinha.getAno(), 1, mesEmpLinha.getValor(), mesEmpLinha.getCredito()));
                empresas.add(mesEmpLinha.getEmpresa());
            }
        }
    }
}
