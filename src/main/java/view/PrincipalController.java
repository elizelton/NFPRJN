package view;

import Config.Config;
import static Config.Config.i18n;
import static Config.DAO.cidadeRepository;
import static Config.DAO.empresaRepository;
import static Config.DAO.mesEmpresaRepository;
import static Config.DAO.mesRepository;
import static Config.DAO.instituicaoRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Cidade;
import model.Empresa;
import model.MesEmpresa;
import model.Mes;
import model.Instituicao;

public class PrincipalController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Instituicao i = new Instituicao("Inst", "22660046000170");
        instituicaoRepository.insert(i);

//Cidade cidade = new Cidade("Ponta Grossa", "PG");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Carambeí", "Cbei");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Tibagi", "Tbg");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Curitiba", "Ctb");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Cascavel", "Casc");
//        cidadeRepository.save(cidade);
////
//        Empresa empresa = new Empresa("Microsoft Corporation", "Microsoft Corporatio", "230131214", "Software", cidadeRepository.findByNome("Curitiba"));
//        empresaRepository.insert(empresa);
//        empresa = new Empresa("Dell Computadores", "Computadores", "84237842", "Computadores", cidadeRepository.findByNome("Cascavel"));
//        empresaRepository.insert(empresa);
//        empresa = new Empresa("Frísia Cooperativa", "Frísia Cooperativa", "9389424793", "Agroindustria", cidadeRepository.findByNome("Carambeí"));
//        empresaRepository.insert(empresa);
//        empresa = new Empresa("Seara Alimentos", "Seara Alimentos", "553453432", "Alimentos", cidadeRepository.findByNome("Ponta Grossa"));
//        empresaRepository.save(empresa);
//        empresa = new Empresa("Construtora Ideal", "Construtora Ideal", "25345434", "Construtora", cidadeRepository.findByNome("Tibagi"));
//        empresaRepository.save(empresa);
//        empresa = new Empresa("Madeiras Mueller", "Madeiras Mueller", "74532454", "Madeireira", cidadeRepository.findByNome("Ponta Grossa"));
//        empresaRepository.save(empresa);
//        empresa = new Empresa("Metalúrgica Balena", "Metalúrgica Balena", "134242134", "Metalurgia", cidadeRepository.findByNome("Cascavel"));
//        empresaRepository.save(empresa);
////
//        Mes meses = new Mes("Janeiro", "2017", 87, 120000.00, 1200.00);
//        mesRepository.insert(meses);
//        meses = new Mes("Fevereiro", "2017", 78, 80000.00, 7320.00);
//        mesRepository.insert(meses);
//        meses = new Mes("Março", "2017", 92, 109327.00, 1321.00);
//        mesRepository.insert(meses);
//        meses = new Mes("Abril", "2017", 67, 74050.00, 600.00);
//        mesRepository.insert(meses);
//        meses = new Mes("Maio", "2017", 74, 62839.00, 832.00);
//        mesRepository.insert(meses);
//        meses = new Mes("Janeiro", "2017", 87, 78540.00, 830.00);
//        mesRepository.insert(meses);
//        meses = new Mes("fevereiro", "2017", 73, 73498.00, 723.00);
//        mesRepository.insert(meses);
//
//        MesEmpresa me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Seara Alimentos"), "Fevereiro", "2017", 20, 1200.00, 1232.0);
//        mesEmpresaRepository.insert(me);
//        me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Dell Computadores"), "Fevereiro", "2017", 12, 31232.00, 342.0);
//        mesEmpresaRepository.insert(me);
//        me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Construtora Ideal"), "Fevereiro", "2017", 32, 3213213.00, 433.0);
//        mesEmpresaRepository.insert(me);
//        me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Frísia Cooperativa"), "Fevereiro", "2017", 231, 1233.00, 213.0);
//        mesEmpresaRepository.insert(me);
//        me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Metalúrgica Balena"), "Fevereiro", "2017", 111, 3213.00, 2321.0);
//        mesEmpresaRepository.insert(me);
//        me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Madeiras Mueller"), "Fevereiro", "2017", 432, 23432.00, 2344.0);
//        mesEmpresaRepository.insert(me);
//        me = new MesEmpresa(empresaRepository.findByNomeFantasiaLikeIgnoreCase("Microsoft Corporation"), "Fevereiro", "2017", 121, 1232332.00, 12312.0);
//        mesEmpresaRepository.insert(me);
//                Meses mes;
//                mes = new Meses("janeiro.text", "2017", 10, 2000.00, 150.00);
//                mes = new Meses(i18n.getString("janeiro.text"), "2017", 10, 2000.00, 150.00 );
//                Meses mes;
//                mes = new Meses("janeiro.text", "2017", 10, 2000.00, 150.00);
//                mesesRepository.save(mes);
//                    MesEmpresa mes = new MesEmpresa (empresaRepository.findByNomeFantasiaLikeIgnoreCase("Tozetto"), "janeiro.text", "2017", 10, 2000.00, 150.00);
//                    mesEmpresaRepository.save(mes);
    }
}
