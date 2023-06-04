package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/Climate.db";
    // public static final String DATABASE = "jdbc:sqlite:database/climate.db";

    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the LGAs in the database.
     *
     * @return Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getLGAs2016() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM LGA WHERE year='2016'";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code = results.getInt("code");
                String name = results.getString("name");

                // Create a LGA Object
                LGA lga = new LGA(code, name, 2016);

                // Add the lga object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return lgas;
    }

    // TODO: Add your required methods here
    public ArrayList<Climate> getLandOceanYears() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                        SELECT Year FROM GlobalLandOceanTemp
                    WHERE GlobalLandOceanTemp.AvgOceanTemp IS NOT NULL
                    AND GlobalLandOceanTemp.MaxOceanTemp IS NOT NULL
                    AND GlobalLandOceanTemp.MinOceanTemp IS NOT NULL
                    """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("year");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }

    public ArrayList<Climate> getAllLandOceanDataASC() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                        SELECT * FROM GlobalLandOceanTemp
                    WHERE GlobalLandOceanTemp.AvgOceanTemp IS NOT NULL
                    AND GlobalLandOceanTemp.MaxOceanTemp IS NOT NULL
                    AND GlobalLandOceanTemp.MinOceanTemp IS NOT NULL
                    """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("year");
                float averageTemperature = results.getFloat("AvgOceanTemp");
                float minimumTemperature = results.getFloat("MinOceanTemp");
                float maximumTemperature = results.getFloat("MaxOceanTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);
                climate.setAverageTemperature(averageTemperature);
                climate.setMinimumTemperature(minimumTemperature);
                climate.setMaximumTemperature(maximumTemperature);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }

    public ArrayList<Climate> getAllLandOceanDataDESC() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                        SELECT * FROM GlobalLandOceanTemp
                    WHERE GlobalLandOceanTemp.AvgOceanTemp IS NOT NULL
                    AND GlobalLandOceanTemp.MaxOceanTemp IS NOT NULL
                    AND GlobalLandOceanTemp.MinOceanTemp IS NOT NULL
                    ORDER BY Year DESC
                    """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("year");
                float averageTemperature = results.getFloat("AvgOceanTemp");
                float minimumTemperature = results.getFloat("MinOceanTemp");
                float maximumTemperature = results.getFloat("MaxOceanTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);
                climate.setAverageTemperature(averageTemperature);
                climate.setMinimumTemperature(minimumTemperature);
                climate.setMaximumTemperature(maximumTemperature);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }

    // Method to get range of global population and temp data (1A)
    public ArrayList<Climate> getPopulationTempRanges() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                    SELECT p.Year, p.PopulationLevel, t.AvgAirTemp, t.MinAirTemp, t.MaxAirTemp
                    FROM CountryPopulation AS p
                    JOIN GlobalTemp AS t ON p.Year = t.Year
                    JOIN Country As c ON p.CountryId = c.CountryId
                    WHERE p.Year IN (1960, 2013)
                    AND c.CountryName = 'World';
                        """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("year");
                long populationLevel = results.getLong("PopulationLevel");
                float averageTemperature = results.getFloat("AvgAirTemp");
                float minimumTemperature = results.getFloat("MinAirTemp");
                float maximumTemperature = results.getFloat("MaxAirTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);
                climate.setPopulationLevel(populationLevel);
                climate.setAverageTemperature(averageTemperature);
                climate.setMinimumTemperature(minimumTemperature);
                climate.setMaximumTemperature(maximumTemperature);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }

    public ArrayList<Climate> getFAQ() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                    SELECT FAQ.Question, FAQ.Answer
                    FROM FAQ
                    ;
                        """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                String question = results.getString("Question");
                String answer = results.getString("Answer");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setQuestion(question);
                climate.setAnswer(answer);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }

    public ArrayList<Climate> getGlobalTempYears() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                    SELECT Year
                    FROM GlobalTemp;
                        """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("year");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }


    public ArrayList<Climate> getCountryClimateData() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query

            String query = """
                    SELECT country.CountryName, countryTemp.Year, countryTemp.AvgTemp, countryTemp.MinTemp, countrytemp.MaxTemp
                    FROM CountryTemp
                    JOIN country ON countryTemp.CountryId = country.CountryId
                    ORDER BY Year;
                            """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                String Name = results.getString("CountryName");
                int year = results.getInt("Year");
                float averageTemperature = results.getFloat("AvgTemp");
                float minimumTemperature = results.getFloat("MinTemp");
                float maximumTemperature = results.getFloat("MaxTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setCountryName(Name);
                climate.setYear(year);
                climate.setAverageTemperature(averageTemperature);
                climate.setMinimumTemperature(minimumTemperature);
                climate.setMaximumTemperature(maximumTemperature);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the climate data
        return climates;
    }

    public ArrayList<Climate> getCountryYearsOfData() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query

            String query = """
                    SELECT DISTINCT CountryTemp.Year
                    FROM CountryTemp
                    ORDER BY Year;
                                """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need


                int year = results.getInt("Year");

                // Create a Climate Object
                Climate climate = new Climate();

                climate.setYear(year);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }


        // Finally we return all of the climate data
        return climates;
    }
  public static HashMap<String, String> getCountryNames() {

        HashMap<String, String> countryNames = new HashMap<String, String>();


        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT CountryName, CountryId from Country DESC;";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                countryNames.put(results.getString("CountryId"), results.getString("CountryName"));
            }

            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return countryNames;
    }


    public static ArrayList<TempData> getTempByState(String countryId, int fromDate, int toDate) {

        ArrayList<TempData> tempByState = new ArrayList<TempData>();


        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT StateName, Year, AvgTemp, MinTemp, MaxTemp from StateTemp WHERE CountryId='" + countryId + "' AND Year >= " + fromDate + " and Year <= " + toDate + ";";


            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                TempData tempData = new TempData(results.getString("StateName"),
                        results.getFloat("AvgTemp"), results.getFloat("MinTemp"),
                        results.getInt("Year"), results.getFloat("MaxTemp"));
                tempByState.add(tempData);
            }
            statement.close();
        }
//
        catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return tempByState;
    }


    public ArrayList<Climate> getCountryPopulationTemp() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                    SELECT cp.Year, c.CountryName, cp.PopulationLevel, ct.AvgTemp
                    FROM CountryPopulation AS cp
                    JOIN CountryTemp AS ct ON cp.Year = ct.Year AND cp.CountryId = ct.CountryId
                    JOIN Country AS c ON cp.CountryId = c.CountryId;
                        """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("Year");
                String countryName = results.getString("CountryName");
                long populationLevel = results.getLong("PopulationLevel");
                float averageTemperature = results.getFloat("AvgTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);
                climate.setCountryName(countryName);
                climate.setPopulationLevel(populationLevel);
                climate.setAverageTemperature(averageTemperature);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }

    public ArrayList<Climate> getWorldPopulationTemp() {
        // Create the ArrayList of Climate objects to return
        ArrayList<Climate> climates = new ArrayList<Climate>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = """
                    SELECT p.Year, c.CountryName, p.PopulationLevel, t.AvgAirTemp
                    FROM CountryPopulation AS p
                    JOIN GlobalTemp AS t ON p.Year = t.Year
                    JOIN Country As c ON p.CountryId = c.CountryId
                    WHERE c.CountryName = 'World';
                        """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                int year = results.getInt("Year");
                String countryName = results.getString("CountryName");
                long populationLevel = results.getLong("PopulationLevel");
                float averageTemperature = results.getFloat("AvgTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setYear(year);
                climate.setCountryName(countryName);
                climate.setPopulationLevel(populationLevel);
                climate.setAverageTemperature(averageTemperature);

                // Add the lga object to the array
                climates.add(climate);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the lga
        return climates;
    }



}

//    public static ArrayList<TempData> getTempByCity(String countryId, String fromDate, String toDate) {
//
//        ArrayList<TempData> stateNames = new ArrayList<TempData>();
//
//
//        Connection connection = null;
//
//        try {
//            // Connect to JDBC data base
//            connection = DriverManager.getConnection(DATABASE);
//
//            // Prepare a new SQL Query & Set a timeout
//            Statement statement = connection.createStatement();
//            statement.setQueryTimeout(30);
//
//            // The Query
//            String query = "SELECT cityName, Year, AvgTemp, MinTemp, MaxTemp from StateTemp WHERE CountryId='" + countryId + "' AND Year > " + fromDate + " and Year < " + toDate + ";";
//
//
//            // Get Result
//            ResultSet results = statement.executeQuery(query);
//
//            // Process all of the results
//            while (results.next()) {
//                // Lookup the columns we need
//                stateNames.add(results.getString("CityName"));
//            }
//            statement.close();
//        }
//
//        catch (SQLException e) {
//            // If there is an error, lets just pring the error
//            System.err.println(e.getMessage());
//        } finally {
//            // Safety code to cleanup
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                // connection close failed.
//                System.err.println(e.getMessage());
//            }
//        }
//
//        // Finally we return all of the lga
//        return stateNames;
//    }
//
//}
