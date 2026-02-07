package com.gbjoanna.rocodromo.controller;


import com.gbjoanna.rocodromo.Util.FileData;
import com.gbjoanna.rocodromo.models.Rocodromo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

public class RocodromoController {

    private ArrayList<Rocodromo> rocodromosList;

    // Variables para recoger los datos del Rocodromo

    private String codigo = " " ;
    private String direccion = " " ;
    private Integer capacidad = 0 ;
    private Integer dificultad = 0 ;
    private Boolean completa = false ;


    @FXML
    private TextField textfieldcodigo;
    @FXML
    private TextField textfielddireccion;
    @FXML
    private TextField textfieldcapacidad;
    @FXML
    private TextField textfielddificultad;
    @FXML
    private CheckBox checkboxcompleta;
    @FXML
    private Button buttonregistrar;
    @FXML
    private Button buttonmodificar;
    @FXML
    private Button buttoneliminar;
    @FXML
    private Label labellist;
    @FXML
    private Label labelaviso;
    @FXML
    private ListView<Rocodromo> listviewrocodromo;

    @FXML
    private ObservableList<Rocodromo> observablelistrocodromos;




    // Controller

    public RocodromoController() {
        if (Files.exists((Path.of(FileData.ROCODROMO_DAT)))) {

            rocodromosList = FileData.loadFile(FileData.ROCODROMO_DAT);

        } else {
            rocodromosList = new ArrayList<>();
        }

    }

    // CRUD rocodromos

    @FXML
    public void initialize() {
        observablelistrocodromos = FXCollections.observableArrayList(rocodromosList);


            listviewrocodromo.setItems(observablelistrocodromos);

            listviewrocodromo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

                if (newValue != null) {
                    textfieldcodigo.setText(newValue.getCodigo());
                    textfielddireccion.setText(newValue.getDireccion());
                    textfieldcapacidad.setText(String.valueOf(newValue.getCapacidad()));
                    textfielddificultad.setText(String.valueOf(newValue.getDificultad()));
                    checkboxcompleta.setSelected(newValue.isCompleta());

                }
            });
    }


    @FXML
    protected void onregistrar(ActionEvent event) {
        codigo = textfieldcodigo.getText();

        if (!codigo.matches("\\d{3}[A-Za-z]")) {
            labelaviso.setText("Dato inválido, verifica el código");
            return;
        }

        direccion = textfielddireccion.getText().trim();
        String capacidadTexto = textfieldcapacidad.getText().trim();

        if (direccion.isEmpty() || capacidadTexto.isEmpty()) {
            labelaviso.setText("Verifica los campos vacios");
            return;
        }

        dificultad = Integer.valueOf(textfielddificultad.getText());
        capacidad = Integer.valueOf(textfieldcapacidad.getText());
        completa = checkboxcompleta.isSelected();

        Rocodromo nuevo = new Rocodromo(codigo, direccion, capacidad, dificultad, completa );
        rocodromosList.add(nuevo);
        observablelistrocodromos.add(nuevo);
        guardarRocodromosList();
        labelaviso.setText("Rocodromo registrado correctamente");

    }

    private void guardarRocodromosList() {
        FileData.saveFile(rocodromosList, FileData.ROCODROMO_DAT);
    }


    @FXML
    protected void onmodificar(ActionEvent event) {

        Rocodromo seleccionado = listviewrocodromo.getSelectionModel().getSelectedItem();

        if  (seleccionado == null) {
            labelaviso.setText("Selecciona el Rocódromo que deseas eliminar");
            return;
        }

        String codigo = textfieldcodigo.getText();

        if (!codigo.matches("\\d{3}[A-Za-z]")) {
            labelaviso.setText("Dato inválido, verifica el código");
            return;
        }

        String direccion = textfielddireccion.getText();
        String capacidadTexto = textfieldcapacidad.getText();

        if (direccion.isEmpty() || capacidadTexto.isEmpty()) {
            labelaviso.setText("Verifica los campos vacios");
            return;
        }

        seleccionado.setCodigo(codigo);
        seleccionado.setDireccion(direccion);
        seleccionado.setCapacidad(Integer.parseInt(capacidadTexto));
        seleccionado.setDificultad(Integer.parseInt(textfielddificultad.getText()));
        seleccionado.setCompleta(checkboxcompleta.isSelected());

        listviewrocodromo.refresh();
        guardarRocodromosList();
        labelaviso.setText("Rocodromo modificado correctamente");

    }


    @FXML
    protected void onEliminar(ActionEvent event) {

        Rocodromo seleccionado = listviewrocodromo.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            labelaviso.setText("Selecciona el Rocódromo que deseas eliminar");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirma si deseeas eliminar el Rocódromo");
        alert.setHeaderText("¿Deseas eliminar el Rocódromo");

        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            rocodromosList.remove(seleccionado);
            observablelistrocodromos.remove(seleccionado);
            guardarRocodromosList();

            labelaviso.setText("Rocodromo eliminado correctamente");

        }




    }
}

