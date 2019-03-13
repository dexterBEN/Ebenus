package com.cours.ebenus.utils;

public class RoleUtils {

    public static final String getRoleByIdQuery = "SELECT * FROM Role WHERE idRole =";
    public static final String getRoleByIdentifantQuery = "SELECT * FROM Role WHERE identifiant =";


    public enum RoleLib {

        ID("idRole"),
        DESCRIPTION("description"),
        IDENTIFIANT("identifiant"),
        VERSION("version");

        String field;

        RoleLib(String filed) {
            this.field = filed;
        }

        public String getField() {
            return field;
        }
    }
}
