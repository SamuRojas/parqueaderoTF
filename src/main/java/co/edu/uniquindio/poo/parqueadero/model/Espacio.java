package co.edu.uniquindio.poo.parqueadero.model;

public class Espacio {
    //Atributos
    private String codigo;
    private TipoEspacio tipoEspacio;
    private EstadoEspacio estadoEspacio;
    //Relaciones
    private Vehiculo theVehiculo;

    //Constructor
    public Espacio ( String codigo){
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estadoEspacio = estadoEspacio;

    }

    public Vehiculo getTheVehiculo() {
        return theVehiculo;
    }

    public String getCodigo() {
        return codigo;
    }

    public EstadoEspacio getEstadoEspacio() {
        return estadoEspacio;
    }

    public TipoEspacio getTipoEspacio() {
        return tipoEspacio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTheVehiculo(Vehiculo theVehiculo) {
        this.theVehiculo = theVehiculo;
    }

    public void setEstadoEspacio(EstadoEspacio estadoEspacio) {
        this.estadoEspacio = estadoEspacio;
    }

    public void setTipoEspacio(TipoEspacio tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }
}
