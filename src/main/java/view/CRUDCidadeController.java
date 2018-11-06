/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static Config.Config.ALTERAR;
import static Config.Config.EXCLUIR;
import static Config.Config.INCLUIR;
import static Config.Config.i18n;
import static Config.DAO.cidadeRepository;
import static Config.DAO.empresaRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cidade;
import org.springframework.data.domain.Sort;

/**
 * FXML Controller class
 *
 * @author Gabriel Strack
 */
public class CRUDCidadeController implements Initializable {

    private CRUDEmpresaController controllerPai;
    @FXML
    private TextField txtFldNome;

    @FXML
    private TextField txtFldSigla;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */

    @FXML
    private void btnCancelaClick()
    {
        anchorPane.getScene().getWindow().hide();
//        controllerPai.tblViewAlunos.requestFocus();
    }

    @FXML
    private void btnConfirmaClick() {
        if (controllerPai.acao == INCLUIR) {
            controllerPai.cidade = new Cidade();
        }
        controllerPai.cidade.setNome(txtFldNome.getText());
        controllerPai.cidade.setSigla(txtFldSigla.getText());
        try {
            switch (controllerPai.acao) {
                case INCLUIR:
                    cidadeRepository.insert(controllerPai.cidade);
                case ALTERAR:
                    cidadeRepository.save(controllerPai.cidade);
                    break;
            }
            controllerPai.cmbBoxCidade.setItems(null);
            controllerPai.cmbBoxCidade.setItems(
                    FXCollections.observableList(cidadeRepository.findAll(
                            new Sort(new Sort.Order("nome")))));
            anchorPane.getScene().getWindow().hide();
        } catch (Exception e) {

     Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(i18n.getString("erro.text"));
            alert.setHeaderText(i18n.getString("erro.cidade.header.text"));
            if (e.getMessage().contains("duplicate key")) {
                alert.setContentText(i18n.getString("erro.cidade.text"));
            } else {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCadastroController(CRUDEmpresaController controllerPai) {
        this.controllerPai = controllerPai;
        txtFldNome.setText(controllerPai.cidade.getNome());
        txtFldSigla.setText(controllerPai.cidade.getSigla());
        
        txtFldNome.setDisable(controllerPai.acao == EXCLUIR);
        txtFldSigla.setDisable(controllerPai.acao == EXCLUIR);
    }
}
