package view;

import Config.Config;
import static Config.Config.i18n;
import static Config.DAO.cidadeRepository;
import static Config.DAO.empresaRepository;
import static Config.DAO.mesEmpresaRepository;
import static Config.DAO.mesesRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Cidade;
import model.Empresa;
import model.MesEmpresa;
import model.Meses;

public class PrincipalController implements Initializable
{

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        Cidade cidade = new Cidade("Ponta Grossa", "PG");
//        cidadeRepository.save(cidade);
//        Cidade cidade = new Cidade("Carambeí", "Cbei");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Tibagi", "Tbg");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Curitiba", "Ctb");
//        cidadeRepository.save(cidade);
//        cidade = new Cidade("Cascavel", "Casc");
//        cidadeRepository.save(cidade);
//
//        Empresa empresa = new Empresa ("Strack venda de computadores", "PC's Strack","122345", "");
//        empresaRepository.save(empresa);
//        Meses mes;
//        mes = new Meses("janeiro.text", "2018", 10, 2000.00, 150.00);

//        mes = new Meses(i18n.getString("janeiro.text"), "2018", 10, 2000.00, 150.00 );

//        Meses mes;
//        mes = new Meses("janeiro.text", "2018", 10, 2000.00, 150.00);

//        mesesRepository.save(mes);

//            MesEmpresa mes = new MesEmpresa (empresaRepository.findByNomeFantasiaLikeIgnoreCase("Tozetto"), "janeiro.text", "2018", 10, 2000.00, 150.00);
//            mesEmpresaRepository.save(mes);
    }
}
