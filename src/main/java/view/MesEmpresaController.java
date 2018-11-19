package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Config.Config.i18n;
import static Config.Config.nf;
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
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.MesEmpresa;
import org.springframework.data.domain.Sort;
import utility.Dados;
import utility.MesEmpresaLinha;

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
//    @FXML
//    private TableView tblViewTotal;
    @FXML
    private ComboBox cmbMes;
    private Dados dados;
    @FXML
    private MaterialDesignIconView btnRemover;
    @FXML
    private MaterialDesignIconView btnAlterar;
    @FXML
    private Label lblProgressBar;
    @FXML
    private ProgressBar progressBar;
    private BufferedReader br = null;

    @FXML
    private StackPane stackPnProgressBar;

    @FXML
    private void acImportarClick() {
        final Task threadImportacao = new Task<Integer>() {
            String linha;

            @Override
            protected Integer call() throws InterruptedException {
                try {
                    int prog = 0, total;
                    br = new BufferedReader(new FileReader(dados.getNomeArq()));
                    total = (int) br.lines().count();
                    br.close();
                    br = new BufferedReader(new FileReader(dados.getNomeArq()));
                    br.skip(170);
                    while ((linha = br.readLine()) != null) {
                        updateProgress(prog, total);
                        updateMessage(nf.format(progressBar.getProgress() * 100) + "%");
                        dados.somaNotas(new MesEmpresaLinha(linha));
                        prog++;
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    if (br != null) {
                        br.close();
                    }
                    return 0;
                } finally {

                    return 0;
                }
            }

        };

        threadImportacao.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                if (newValue == Worker.State.SUCCEEDED) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Sucesso");
                    alert.setHeaderText("Finalizado titulo");
                    alert.setContentText("concluido");
                    alert.showAndWait();
                }
            }

        });
        final Stage stage = null;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolha o seu arquivo");

        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
//        dados = new Dados("F:\\DEV\\Java\\NFPRJN\\DOCTESTE.csv");
//        dados = new Dados("F:\\DEV\\Java\\NFPRJN\\DocFiscal-01052017.csv");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "CSV Files", "*.csv"
        );
        fileChooser.getExtensionFilters().add(extFilter);
        dados = new Dados(String.valueOf(fileChooser.showOpenDialog(stage)));
        if (dados.validaImportacao()) {
            Thread t = new Thread(threadImportacao);
            t.setDaemon(true);
            t.start();
            stackPnProgressBar.visibleProperty().bind(threadImportacao.runningProperty());
            lblProgressBar.textProperty().bind(threadImportacao.messageProperty());
            progressBar.progressProperty().bind(threadImportacao.progressProperty());
            try {
                mesRepository.insert(dados.getMes());
                mesEmpresaRepository.insert(dados.getMesEmpresas());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Falha na importação!");
                if (e.getMessage().contains("duplicate key")) {
                    alert.setContentText("O mês ja foi importado");
                } else {
                    alert.setContentText(e.getMessage());
                }
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Falha na importação!");
            alert.setContentText("Os dados não pertencem a instuição cadastrada.");
            alert.showAndWait();
        }

        cmbMes.setItems(FXCollections.observableArrayList(mesRepository.findAll()));
        cmbMes.getSelectionModel().selectFirst();
        tblViewNotas.setItems(FXCollections.observableList(mesEmpresaRepository.findAll()));

    }

    @FXML
    private void acNaoSeiOqFaz() {

    }

    public void somaNotas(MesEmpresaLinha mesEmpLinha) {
//        if (mesEmpLinha.getEmpresa() != null) {
//            int elemList = empresas.indexOf(mesEmpLinha.getEmpresa());
//            totalNotas++;
//            totalCredito += mesEmpLinha.getCredito();
//            totalValorNotas += mesEmpLinha.getValor();
//            if (elemList > -1) {
//                mesEmpresas.get(elemList).somaNota(mesEmpLinha.getValor(), mesEmpLinha.getCredito());
//            } else {
//                mesEmpresas.add(new MesEmpresa(mesEmpLinha.getEmpresa(), mesEmpLinha.getMes(), mesEmpLinha.getAno(), 1, mesEmpLinha.getValor(), mesEmpLinha.getCredito()));
//                empresas.add(mesEmpLinha.getEmpresa());
//            }
//        }
        System.out.println(mesEmpLinha);
    }

    @FXML
    private void acRemover() {
        Mes mesSelecionado = (Mes) cmbMes.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                i18n.getString("txt.sair.text"),
                ButtonType.YES, ButtonType.NO);
        alert.setTitle(i18n.getString("lbl.excluir.text"));
        alert.showAndWait();
        if (alert.getResult() == ButtonType.NO) {
            alert.close();
        } else {
            mesRepository.delete(mesSelecionado);
            mesEmpresaRepository.delete(mesEmpresaRepository.findByAnoAndMes(mesSelecionado.getAno(), mesSelecionado.getMes()));
            cmbMes.setItems(FXCollections.observableArrayList(mesRepository.findAll()));
            cmbMes.getSelectionModel().selectFirst();
            tblViewNotas.setItems(FXCollections.observableList(mesEmpresaRepository.findAll()));
        }
    }

    @FXML
    private void acAlterar() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        tblViewTotal.getStyleClass().add("noheader");
        cmbMes.setItems(FXCollections.observableArrayList(mesRepository.findAll()));
        cmbMes.getSelectionModel().selectFirst();
        btnRemover.visibleProperty().bind(cmbMes.valueProperty().isNotNull());
        btnAlterar.visibleProperty().bind(cmbMes.valueProperty().isNotNull());
        tblViewNotas.setItems(FXCollections.observableList(mesEmpresaRepository.findAll()));
        cmbMes.valueProperty().addListener(
                new ChangeListener<Mes>() {

            public void changed(ObservableValue<? extends Mes> observable, Mes oldValue, Mes newValue) {
                if (newValue != null) {
                    tblViewNotas.setItems(FXCollections.observableList(mesEmpresaRepository.findByAnoAndMes(newValue.getAno(), newValue.getMes())));
//            lblTotalCredito.setText(Double.toString(newValue.getTotalCredito()));
//            lblTotalNotas.setText(Integer.toString(newValue.getTotalNotas()));
//            lblTotalValNotas.setText(Double.toString(newValue.getTotalValor()));
                }
            }
        }
        );
    }
}
