package view;

import static Config.DAO.cidadeRepository;
import static Config.DAO.empresaRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Cidade;
import model.Empresa;

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
    }
}
