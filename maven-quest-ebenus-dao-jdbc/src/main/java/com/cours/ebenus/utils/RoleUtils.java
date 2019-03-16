package com.cours.ebenus.utils;

public class RoleUtils {

    public static final String getRoleByIdQuery = "SELECT * FROM Role WHERE idRole =";
    public static final String getRoleByIdentifantQuery = "SELECT * FROM Role WHERE identifiant = ";
    public static final String getAllRoleQuery = "SELECT * FROM Role";
    public static final String createRoleQuery = "INSERT INTO `Role` (identifiant, description, version) VALUES (";
    public static final String updateRoleQuery = "UPDATE `Role` SET identifiant =";
    public static final String deleteRoleQuery = "DELETE FROM `Role` WHERE idRole =";


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
