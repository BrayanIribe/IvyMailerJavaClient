/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IvyMailer.Model;

/**
 *
 * @author lv1822
 */
public class Alumno extends objetosNegocio.Alumno {
    
    public Alumno() {
        super();
    }

    public Alumno(String clave, String nombre, String email, String carrera) {
        super(clave, nombre, email, carrera);
    }

    public Alumno(String clave) {
        super(clave);
    }
}
