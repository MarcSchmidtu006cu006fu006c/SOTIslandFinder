package dataAccess;

import java.sql.*;



public class Database {
    /**
     * Please note that in a production enviorment the user connecting to the database would not be the root user nor
     * would the password used be "password" and stored in plane text. This is a demonstration and will never be used
     * in a public manner.
     */
    static final String connUrl = "jdbc:mysql://127.0.0.1:3306/?user=root";
    static final String user = "root";
    static final String pass = "password";
    static final String semiColin = "\";";
    static int idNum = 0;
    String selectQuery = "SELECT image_location FROM island.island_images WHERE id=\"";
    String idNumString = "";
    String imageLocation = "";

    /**
     *
     * @param id the id parameter is used to retrieve an image location from the database based on the id of the data row
     * @return a string is returned containing the local file path of the image that was retrieved from the database
     * @throws Exception An SQL execution may be thrown if there is an issue connecting with the url, username, or
     * password
     */
    public String getIslandFile(int id) throws Exception {
        //The id number is incremented everytime the method is called to retrieve the next row in the table from the database.
        idNum++;
        //The id is put into a string so that it can be used in the query of the select statement
        idNumString = String.valueOf(idNum);

        //Below is the connection information provided to the database as well as the forName statement to use the JDBC connector
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(connUrl, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery + idNumString + semiColin)
        ) {
            while (rs.next()) {
                //The database return the file path of the image and it is assigned to the string variable imageLocation
                imageLocation = rs.getString("image_location");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //The file path is returned once the method is done execution
        return imageLocation;
    }

    /**
     *
     * @param id the id paramater is used to locate the row in the database's table that is to be selected
     * @return The name and location of the island image identified is returned
     */
    public String getIslandLocation(int id) {
        String islandCoordinatesLetter = "";
        String islandName = "";
        String locationName = "";
        int islandCoordinatesNum = 0;

        //The connection information it is created for connecting to the database, there is no forName method call as it
        //is already call in the getIsland file method
        try (Connection conn = DriverManager.getConnection(connUrl, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT number_value, letter_value, island_name FROM island.island_images WHERE id=" + id + ";")
        ) {
            while (rs.next()) {
                //The confidantes and island name are placed into string variables
                islandCoordinatesNum = rs.getInt("number_value");
                islandCoordinatesLetter = rs.getString("letter_value");
                islandName = rs.getString("island_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //The location and name of the island are combined into the locationName variable to be returned
        locationName = islandName + " It can be found at " + islandCoordinatesLetter + islandCoordinatesNum;
        return locationName;
    }


}
