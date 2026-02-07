package com.gbjoanna.rocodromo.controller;


import com.gbjoanna.rocodromo.Util.FileData;
import com.gbjoanna.rocodromo.models.Rocodromo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class rocodromoController {

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
    private ListView<String> listviewrocodromo;
    @FXML
    private ObservableList<Rocodromo> observablelistrocodromo;




    // Controller

    public rocodromoController() {
        if (Files.exists((Path.of(FileData.ROCODROMO_DAT)))) {

            rocodromosList = FileData.loadFile(FileData.ROCODROMO_DAT);

        } else {
            rocodromosList = new ArrayList<>();
        }

        observablelistrocodromo = FXCollections.observableArrayList(rocodromosList);

       // javafx.application.Platform.runLater(() -> {
        //    listviewrocodromos.setItems(observablelistrocodromo)
     //   }
    }

    // CRUD rocodromos


    @FXML
    protected void onregistrar(ActionEvent event) {

        codigo = textfieldcodigo.getText();

        if (!codigo.matches("d{3}[A-Za-z]")) {
            labelaviso.setText("Dato inválido, verifica el código");
            return;
        }

        dificultad = Integer.valueOf(textfielddificultad.getText());

        completa = checkboxcompleta.isSelected();

        direccion = textfielddireccion.getText().trim();
        String capacidadTexto = textfieldcapacidad.getText().trim();

        if (direccion.isEmpty() || capacidadTexto.isEmpty()) {
            labelaviso.setText("Verifica los campos vacios");
            return;
        }

        guardarRocodromosList();
    }

        private void guardarRocodromosList() {
            FileData.saveFile(rocodromosList, FileData.ROCODROMO_DAT);
            rocodromosList = FileData.loadFile(FileData.ROCODROMO_DAT);
        }


    @FXML
    protected void onmodificar(ActionEvent event) {

        if (!codigo.matches("d{3}[A-Za-z]")) {
            labelaviso.setText("Dato inválido, verifica el código");
            return;
        }


        String direccion = textfielddireccion.getText();
        String capacidadTexto = textfieldcapacidad.getText();

        if (direccion.isEmpty() || capacidadTexto.isEmpty()) {
            labelaviso.setText("Verifica los campos vacios");
            return;
        }



    }

    @FXML
    protected void oneliminar(ActionEvent event) {
        //seleccionar listiviewRoco
        //Hacer el null con el if
        //if (seleccionado == null) {
        // labelaviso....
        //lista.remove
        //puedo ingresar un alerta con Alert alert.. verificar en hoteles


    }
}

