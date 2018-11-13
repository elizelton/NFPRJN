package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Config.DAO.mesEmpresaRepository;
import static Config.DAO.mesRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import model.Mes;
import Repository.MesRepository;

/**
 * FXML Controller class
 *
 * @author elizelton.santos
 */
public class MesEmpresaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tblViewNotas;
    @FXML
    private TableView tblViewTotal;
    @FXML
    private ComboBox cmbMes;

    @FXML
    private void acImportar() {

    }

    @FXML
    private void acNaoSeiOqFaz() {

    }

    @FXML
    private void acRemover() {

    }

    @FXML
    private void acAlterar() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblViewTotal.getStyleClass().add("noheader");
        cmbMes.setItems(FXCollections.observableArrayList(mesRepository.findAll()));
        cmbMes.getSelectionModel().selectFirst();

        tblViewNotas.setItems(FXCollections.observableList(mesEmpresaRepository.findAll()));
    }

    @FXML
    private void cbmMesClicked() {
        Mes mesSelec = (Mes) cmbMes.getSelectionModel().getSelectedItem();
        if (mesSelec != null) {
            tblViewNotas.setItems(FXCollections.observableList(mesEmpresaRepository.findByAnoAndMes(mesSelec.getAno(), mesSelec.getMes())));
            tblViewTotal.setItems(FXCollections.observableList(mesRepository.findByAnoAndMes(mesSelec.getAno(), mesSelec.getMes())));
        }
    }
}
