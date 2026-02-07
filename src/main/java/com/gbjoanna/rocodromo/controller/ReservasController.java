package com.gbjoanna.rocodromo.controller;

import com.gbjoanna.rocodromo.Util.FileData;
import com.gbjoanna.rocodromo.models.Reserva;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.events.MouseEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.server.RemoteServer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservasController {

    // Variables para recoger los datos
    private float precio = 0.0F;
    private LocalDate fechaReserva;
    private String nReserva = "";
    private String estado = "";
    private String tipoPago = "";
    // lista guardado reservas
    private ArrayList<Reserva> reservaList;
    // Lista  estado y tipo reserva
    private final ArrayList<String> listEstadoReserva = Reserva.getEstadoReserva();
    private final ArrayList<String> listTipoPagos = Reserva.getTiposPagos();

    // Components of the UI
    @FXML
    private Label labelAviso;
    @FXML
    private Label labelprecio;
    @FXML
    private Label labelnumreserva;
    @FXML
    private Label Estado;
    @FXML
    private Label Dificultad;
    @FXML
    private Label Fecha;
    @FXML
    private TextField textfieldprecio;
    @FXML
    private TextField textfieldnumreserva;
    @FXML
    private TextField textfielestado;
    @FXML
    private TextField textfieldtipopago;
    @FXML
    private DatePicker datepickerfecha;
    @FXML
    private ListView<String> listViewReservas;
    @FXML
    private Button buttonmodificar;
    @FXML
    private Button buttoneliminar;
    @FXML
    private Button buttonregistrar;

    // Contructor
    public ReservasController() {
        //Cargar de disco
        if (Files.exists((Path.of(FileData.RESERVAS_DAT))))
        {   // if exist
            reservaList = FileData.loadFile(FileData.RESERVAS_DAT);
        } else {
            // inicizar array
            reservaList = new ArrayList<>();
        }
    }

    // CRUD Reservas (Action BUtton)
    @FXML
    protected void onRegistrar(ActionEvent event) {
        // Chekeo datos
        if(!checkFields())
            return;
        // recoger datos
        if(!collectData())
            return;
        // Guardar datos
        saveFileRefreshReservasList();
        // refrescar listview
        refreshListView(reservaList);

    }

    @FXML
    protected void onModificar(ActionEvent event) {
        // Chekeo datos
        if(!checkFields())
            return;
        // recoger datos
        if(!collectData())
            return;
        // Guardar datos
        saveFileRefreshReservasList();
        // refrescar listview
        refreshListView(reservaList);
    }

    @FXML
    protected void onEliminar(ActionEvent event) {
        var nReserva = textfieldnumreserva.getText();
        for(Reserva reserva : reservaList) {
            if (nReserva.equals(reserva.getnReserva())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Borrado de datos");
                alert.setContentText("El borrado será permanente. ¿Deseas continuar?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    reservaList.remove(reserva);
                    labelAviso.setText("El dispositivo ha sido borrado correctamente.");
                    refreshListView(reservaList);
                    saveFileRefreshReservasList();
                    clearData();
                    break;
                } else {
                    labelAviso.setText("La operación ha sido cancelada.");
                    clearData();
                    break;
                }
            }
        }
    }
    // Otras funciones
    @FXML
    protected  void onMouseClickedListView(MouseEvent event) {
        clearData();
        for (Reserva numReserva: reservaList) {
            if (numReserva.getnReserva().equals(listViewReservas.getSelectionModel().getSelectedItem()))
            {
                showDataInField(numReserva.getnReserva());
                break;
            }
        }
    }

    /// //////
    // Check datos
    private boolean checkFields() {
        // ver que los dato no están vacíos
        if (textfieldprecio.getText().isEmpty() || textfieldnumreserva.getText().isEmpty())
        {
            labelAviso.setText("Los campos precio y número de reserva deben tener datos");
            return false;
        }

        return true;
    }

    // coger datos
    private boolean collectData() {
        precio = Float.parseFloat(textfieldprecio.getText());
        fechaReserva = datepickerfecha.getValue();
        nReserva = textfieldnumreserva.getText();
        estado = textfielestado.getText();
        tipoPago = textfieldtipopago.getText();

        if (!nReserva.matches("\\d{4}[A-Za-z]")) {
            labelAviso.setText("El código introducido es erróneo");
            return false;
        }

        return true;
    }

    // Guardar datos en fichero
    private void saveFileRefreshReservasList() {
        Reserva reserva = new Reserva(precio,nReserva,fechaReserva,estado,tipoPago);
        reservaList.add(reserva);
        FileData.saveFile(reservaList, FileData.RESERVAS_DAT);
        reservaList = FileData.loadFile(FileData.RESERVAS_DAT);
    }

    // Mostrar datos
    private void showDataInField(String nReserva ) {
        for( Reserva reserva : reservaList) {
            // pintar fields
            textfieldprecio.setText(Float.toString(reserva.getPrecio()));
            textfieldnumreserva.setText(reserva.getnReserva());
            textfielestado.setText(reserva.getEstado());
            textfieldtipopago.setText(reserva.getTipoPago());
            datepickerfecha.setValue(reserva.getFechaReserva());
        }
    }
    // Limpiar datos
    private  void clearData(){
        textfieldprecio.setText("");
        textfieldnumreserva.setText("");
        textfielestado.setText("");
        textfieldtipopago.setText("");
        datepickerfecha.setValue(null);
    }

     // Refrescar listview
    private void refreshListView(List<Reserva> reservas) {
        // limpiamos lista
        listViewReservas.getItems().clear();
        for (Reserva reserva : reservas) {
            listViewReservas.getItems().add(reserva.getnReserva());
        }


    }
}
