package com.example.matriculacionalumno.database.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "alumno_rel_asignatura",
        primaryKeys = {"dni", "codigo"},
        foreignKeys = {
                @ForeignKey(
                        entity = Alumno.class,
                        parentColumns = "dni",
                        childColumns = "dni",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(
                        entity = Asignatura.class,
                        parentColumns = "codigo",
                        childColumns = "codigo")
        }
)
public class AlumnoRelAsignatura {
    @NonNull
    @ColumnInfo(name = "dni")
    public String dni;

    @NonNull
    @ColumnInfo(name = "codigo")
    public String codigo;

    public AlumnoRelAsignatura(@NonNull String dni, @NonNull String codigo) {
        this.codigo = codigo;
        this.dni = dni;
    }

    @NonNull
    public String getDni() {
        return dni;
    }

    public void setDni(@NonNull String dni) {
        this.dni = dni;
    }

    @NonNull
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NonNull String codigo) {
        this.codigo = codigo;
    }
}
