package controller;

import model.Class.db.DatabaseHandler;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class ValidatingRegisterInput {
    public static boolean checkAllInput(HashMap<String, String> allInput, char[][] allPass){

        if(allPass[0] == null || allPass[1] == null){
            return false;
        }

        for(String input : allInput.values()){
            if(input.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static String checkToDB(HashMap<String, String> tempInputs) {
        DatabaseHandler conn = new DatabaseHandler();
        conn.connect();
        Statement usernameStatement;
        Statement emailStatement;
        Statement phoneStatement;
        ResultSet resultSet;

        try {
            String queryUsername = "SELECT username FROM users WHERE username = '" + tempInputs.get("username") + "'";
            usernameStatement = conn.con.createStatement();
            resultSet = usernameStatement.executeQuery(queryUsername);
            if (resultSet.next()) {
                return "username";
            }

            String queryEmail = "SELECT email FROM users WHERE email = '" + tempInputs.get("email")+ "'";
            emailStatement = conn.con.createStatement();
            resultSet = emailStatement.executeQuery(queryEmail);
            if (resultSet.next()) {
                return "email";
            }

            String queryPhone_Number = "SELECT Phone_Number FROM users WHERE Phone_Number = '" + tempInputs.get("phone")+ "'";
            phoneStatement = conn.con.createStatement();
            resultSet = phoneStatement.executeQuery(queryPhone_Number);
            if (resultSet.next()) {
                return "phone";
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return "true";
    }

    public static boolean checkPassword(char[] pass, char[] verifyPass) {
        int i = 0;

        if (pass.length != verifyPass.length) {
            return false;
        }

        while (i < pass.length) {
            if (pass[i] != verifyPass[i]) {
                return false;
            }
            i++;
        }

        return true;
    }
}
