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
                    Select * From HeatMapView;
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

    public ArrayList<Climate> getCountryClimateAndPopulationDataForGraph() {
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
                    SELECT country.CountryName, countryTemp.Year, countryPopulation.PopulationLevel, countryTemp.AvgTemp, countryTemp.MinTemp, countrytemp.MaxTemp
                    FROM CountryTemp
                    JOIN country ON countryTemp.CountryId = country.CountryId
                    JOIN CountryPopulation ON CountryTemp.year = CountryPopulation.year
                    GROUP BY COUNTRY.CountryName
                    ORDER BY countryTemp.Year;
                                """;

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                String Name = results.getString("CountryName");
                int year = results.getInt("Year");
                long population = results.getLong("PopulationLevel");
                float averageTemperature = results.getFloat("AvgTemp");
                float minimumTemperature = results.getFloat("MinTemp");
                float maximumTemperature = results.getFloat("MaxTemp");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setCountryName(Name);
                climate.setYear(year);
                climate.setPopulationLevel(population);
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

    public static ArrayList<Country> getCountryNames() {

        ArrayList<Country> countryNames = new ArrayList<>();

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
                countryNames.add(new Country(results.getString("CountryId"),
                results.getString("CountryName")));
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
            String query = "SELECT StateName, Year, AvgTemp, MinTemp, MaxTemp from StateTemp WHERE CountryId='"
                    + countryId + "' AND Year >= " + fromDate + " and Year <= " + toDate + " AND AvgTemp != 0 AND MinTemp != 0 AND MaxTemp != 0;";

            // Get Result
            ResultSet results = statement.executeQuery(query);


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

    public static ArrayList<TempData> getTempByCity(String countryId, int fromDate, int toDate) {

        ArrayList<TempData> tempByCity = new ArrayList<TempData>();

        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query

           // The Query
           String query = "SELECT CityName, Year, AvgTemp, MinTemp, MaxTemp from CityTemp WHERE CountryId='" + countryId + "' AND Year >= " + fromDate + " and Year <= " + toDate + " AND AvgTemp != 0 AND MinTemp != 0 AND MaxTemp != 0;";


           // Get Result
           ResultSet results = statement.executeQuery(query);

           // Process all of the results
          while (results.next()) {
               // Lookup the columns we need

                TempData tempData = new TempData(results.getString("CityName"),
                        results.getFloat("AvgTemp"), results.getFloat("MinTemp"),
                        results.getInt("Year"), results.getFloat("MaxTemp"));
                tempByCity.add(tempData);
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
        return tempByCity;
    }

    public ArrayList<Climate> getCountryPopulationTemp(String startYear, String endYear, String type, String sort) {
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
            String query = "SELECT c.CountryName, pstart.PopulationLevel AS startp, pend.PopulationLevel AS endp, ";
            query = query
                    + "(CAST(pend.PopulationLevel AS FLOAT) - CAST(pstart.PopulationLevel AS FLOAT)) / CAST(pstart.PopulationLevel AS FLOAT) * 100 AS percentagep, ";
            query = query + "tstart.AvgTemp AS startt, tend.AvgTemp AS endt, ";
            query = query + "(tend.AvgTemp - tstart.AvgTemp) / tstart.AvgTemp * 100 AS percentaget ";
            query = query + "FROM CountryPopulation AS pstart ";
            query = query + "JOIN CountryPopulation AS pend ON pstart.CountryId = pend.CountryId ";
            query = query
                    + "JOIN CountryTemp AS tstart ON tstart.Year = pstart.Year AND tstart.CountryId = pstart.CountryId ";
            query = query + "JOIN CountryTemp AS tend ON tend.Year = pend.Year AND tend.CountryId = pend.CountryId ";
            query = query + "JOIN Country AS c ON pstart.CountryId = c.CountryId ";
            query = query + "WHERE pstart.Year = '" + startYear + "' ";
            query = query + "AND pend.Year = '" + endYear + "' ";
            query = query + "ORDER BY " + type + " " + sort + ";";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                String countryName = results.getString("CountryName");
                long startPopulationLevel = results.getLong("startp");
                long endPopulationLevel = results.getLong("endp");
                float populationPercent = results.getFloat("percentagep");
                float startTemp = results.getFloat("startt");
                float endTemp = results.getFloat("endt");
                float tempPercent = results.getFloat("percentaget");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setCountryName(countryName);
                climate.setStartPopulation(startPopulationLevel);
                climate.setEndPopulation(endPopulationLevel);
                climate.setPopulationPercent(populationPercent);
                climate.setStartTemp(startTemp);
                climate.setEndTemp(endTemp);
                climate.setTempPercent(tempPercent);

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

    public ArrayList<Climate> getWorldPopulationTemp(String startYear, String endYear) {
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
            String query = "SELECT c.CountryName, pstart.PopulationLevel AS startp, pend.PopulationLevel AS endp, ";
            query = query
                    + "(CAST(pend.PopulationLevel AS FLOAT) - CAST(pstart.PopulationLevel AS FLOAT)) / CAST(pstart.PopulationLevel AS FLOAT) * 100 AS percentagep, ";
            query = query + "tstart.AvgAirTemp AS startt, tend.AvgAirTemp AS endt, ";
            query = query + "(tend.AvgAirTemp - tstart.AvgAirTemp) / tstart.AvgAirTemp * 100 AS percentaget ";
            query = query + "FROM CountryPopulation AS pstart ";
            query = query + "JOIN CountryPopulation AS pend ON pstart.CountryId = pend.CountryId ";
            query = query + "JOIN GlobalTemp AS tstart ON tstart.Year = pstart.Year ";
            query = query + "JOIN GlobalTemp AS tend ON tend.Year = pend.Year ";
            query = query + "JOIN Country AS c ON pstart.CountryId = c.CountryId ";
            query = query + "WHERE c.CountryName = 'World' ";
            query = query + "AND pstart.Year = '" + startYear + "' ";
            query = query + "AND pend.Year = '" + endYear + "';";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need

                String countryName = results.getString("CountryName");
                long startPopulationLevel = results.getLong("startp");
                long endPopulationLevel = results.getLong("endp");
                float populationPercent = results.getFloat("percentagep");
                float startTemp = results.getFloat("startt");
                float endTemp = results.getFloat("endt");
                float tempPercent = results.getFloat("percentaget");

                // Create a Climate Object
                Climate climate = new Climate();
                climate.setCountryName(countryName);
                climate.setStartPopulation(startPopulationLevel);
                climate.setEndPopulation(endPopulationLevel);
                climate.setPopulationPercent(populationPercent);
                climate.setStartTemp(startTemp);
                climate.setEndTemp(endTemp);
                climate.setTempPercent(tempPercent);

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

    public ArrayList<Climate> getWorldLandOceanAverageTempOverPeriod(ArrayList<String> startYears,
            ArrayList<String> endYears,
            ArrayList<String> dataTypes, String OrderBy) {
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
            String query = "";
            for (int i = 0; i < startYears.size(); i++) {
                if (startYears.size() == 1 || i == startYears.size() - 1) {
                    if (dataTypes.get(i).equals("Land Data")) {
                        query = query + "SELECT '" + dataTypes.get(i) + "' AS DataType, SY.StartYear, ";
                        query = query + "ROUND(SY.StartYearAvg, 2) AS StartYearAvg, ";
                        query = query
                                + "EY.EndYear, ROUND(EY.EndYearAvg, 2) AS EndYearAvg, ROUND(AVG(ATOP.AvgAirTemp), 2) AS AverageTempOverPeriod, ROUND(ABS((EY.EndYearAvg - SY.StartYearAvg) / SY.StartYearAvg) * 100, 2) AS ChangeInAvgTempPercentage ";
                        query = query
                                + "FROM (SELECT Year AS StartYear, AvgAirTemp AS StartYearAvg FROM GlobalTemp WHERE Year = '"
                                + startYears.get(i) + "') AS SY ";
                        query = query
                                + "JOIN (SELECT Year AS EndYear, AvgAirTemp AS EndYearAvg FROM GlobalTemp WHERE Year = '"
                                + endYears.get(i) + "') AS EY JOIN GlobalTemp AS ATOP ";
                        query = query
                                + "ON ATOP.Year BETWEEN SY.StartYear AND EY.EndYear GROUP BY SY.StartYear, EY.EndYear ";
                    } else if (dataTypes.get(i).equals("Land-Ocean Data")) {
                        // The Query
                        query = query + "SELECT '" + dataTypes.get(i) + "' AS DataType, SY.StartYear, ";
                        query = query + "ROUND(SY.StartYearAvg, 2) AS StartYearAvg, ";
                        query = query
                                + "EY.EndYear, ROUND(EY.EndYearAvg, 2) AS EndYearAvg, ROUND(AVG(ATOP.AvgOceanTemp), 2) AS AverageTempOverPeriod, ROUND(ABS((EY.EndYearAvg - SY.StartYearAvg) / SY.StartYearAvg) * 100, 2) AS ChangeInAvgTempPercentage ";
                        query = query
                                + "FROM (SELECT Year AS StartYear, AvgOceanTemp AS StartYearAvg FROM GlobalLandOceanTemp WHERE Year = '"
                                + startYears.get(i) + "') AS SY ";
                        query = query
                                + "JOIN (SELECT Year AS EndYear, AvgOceanTemp AS EndYearAvg FROM GlobalLandOceanTemp WHERE Year = '"
                                + endYears.get(i) + "') AS EY JOIN GlobalLandOceanTemp AS ATOP ";
                        query = query
                                + "ON ATOP.Year BETWEEN SY.StartYear AND EY.EndYear GROUP BY SY.StartYear, EY.EndYear ";
                    }
                } else {

                    if (dataTypes.get(i).equals("Land Data")) {
                        query = query + "SELECT '" + dataTypes.get(i) + "' AS DataType, SY.StartYear, ";
                        query = query + "ROUND(SY.StartYearAvg, 2) AS StartYearAvg, ";
                        query = query
                                + "EY.EndYear, ROUND(EY.EndYearAvg, 2) AS EndYearAvg, ROUND(AVG(ATOP.AvgAirTemp), 2) AS AverageTempOverPeriod, ROUND(ABS((EY.EndYearAvg - SY.StartYearAvg) / SY.StartYearAvg) * 100, 2) AS ChangeInAvgTempPercentage ";
                        query = query
                                + "FROM (SELECT Year AS StartYear, AvgAirTemp AS StartYearAvg FROM GlobalTemp WHERE Year = '"
                                + startYears.get(i) + "') AS SY ";
                        query = query
                                + "JOIN (SELECT Year AS EndYear, AvgAirTemp AS EndYearAvg FROM GlobalTemp WHERE Year = '"
                                + endYears.get(i) + "') AS EY JOIN GlobalTemp AS ATOP ";
                        query = query
                                + "ON ATOP.Year BETWEEN SY.StartYear AND EY.EndYear GROUP BY SY.StartYear, EY.EndYear ";
                        query = query + "UNION ";

                    } else if (dataTypes.get(i).equals("Land-Ocean Data")) {
                        // The Query
                        query = query + "SELECT '" + dataTypes.get(i) + "' AS DataType, SY.StartYear, ";
                        query = query + "ROUND(SY.StartYearAvg, 2) AS StartYearAvg, ";
                        query = query
                                + "EY.EndYear, ROUND(EY.EndYearAvg, 2) AS EndYearAvg, ROUND(AVG(ATOP.AvgOceanTemp), 2) AS AverageTempOverPeriod, ROUND(ABS((EY.EndYearAvg - SY.StartYearAvg) / SY.StartYearAvg) * 100, 2) AS ChangeInAvgTempPercentage ";
                        query = query
                                + "FROM (SELECT Year AS StartYear, AvgOceanTemp AS StartYearAvg FROM GlobalLandOceanTemp WHERE Year = '"
                                + startYears.get(i) + "') AS SY ";
                        query = query
                                + "JOIN (SELECT Year AS EndYear, AvgOceanTemp AS EndYearAvg FROM GlobalLandOceanTemp WHERE Year = '"
                                + endYears.get(i) + "') AS EY JOIN GlobalLandOceanTemp AS ATOP ";
                        query = query
                                + "ON ATOP.Year BETWEEN SY.StartYear AND EY.EndYear GROUP BY SY.StartYear, EY.EndYear ";
                        query = query + "UNION ";
                    }
                }
            }
            if (OrderBy.equals("Descending")) {
                query = query + "ORDER BY ChangeInAvgTempPercentage DESC ";
            } else if (OrderBy.equals("Ascending")) {
                query = query + "ORDER BY ChangeInAvgTempPercentage ";
            }
            query = query + "; ";
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                String dataType = results.getString("DataType");
                int startYear = results.getInt("StartYear");
                float startTemp = results.getFloat("StartYearAvg");
                int endYear = results.getInt("EndYear");
                float endTemp = results.getFloat("EndYearAvg");
                float averageTempOverPeriod = results.getFloat("AverageTempOverPeriod");
                float tempPercentage = results.getFloat("ChangeInAvgTempPercentage");
                // Create a Climate Object
                Climate climate = new Climate();
                climate.setDataType(dataType);
                climate.setStartYear(startYear);
                climate.setStartTemp(startTemp);
                climate.setEndYear(endYear);
                climate.setEndTemp(endTemp);
                climate.setAverageTemperature(averageTempOverPeriod);
                climate.setTempPercent(tempPercentage);
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

    public ArrayList<Climate> OLDgetWorldLandOceanAverageTempOverPeriod(String startYear, String endYear,
            String dataType) {
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

            if (dataType.equals("Land Data")) {
                // The Query
                String query = "SELECT SY.StartYear, ";
                query = query + "ROUND(SY.StartYearAvg, 2) AS StartYearAvg, ";
                query = query
                        + "EY.EndYear, ROUND(EY.EndYearAvg, 2) AS EndYearAvg, ROUND(AVG(ATOP.AvgAirTemp), 2) AS AverageTempOverPeriod, ROUND(((EY.EndYearAvg - SY.StartYearAvg) / SY.StartYearAvg) * 100, 2) AS ChangeInAvgTempPercentage ";
                query = query
                        + "FROM (SELECT Year AS StartYear, AvgAirTemp AS StartYearAvg FROM GlobalTemp WHERE Year = '"
                        + startYear + "') AS SY ";
                query = query
                        + "JOIN (SELECT Year AS EndYear, AvgAirTemp AS EndYearAvg FROM GlobalTemp WHERE Year = '"
                        + endYear + "') AS EY JOIN GlobalTemp AS ATOP ";
                query = query + "ON ATOP.Year BETWEEN SY.StartYear AND EY.EndYear GROUP BY SY.StartYear, EY.EndYear; ";

                // Get Result
                ResultSet results = statement.executeQuery(query);

                // Process all of the results
                while (results.next()) {
                    // Lookup the columns we need

                    float startTemp = results.getFloat("StartYearAvg");
                    float endTemp = results.getFloat("EndYearAvg");
                    float averageTempOverPeriod = results.getFloat("AverageTempOverPeriod");
                    float tempPercentage = results.getFloat("ChangeInAvgTempPercentage");
                    // Create a Climate Object
                    Climate climate = new Climate();
                    climate.setDataType(dataType);
                    climate.setStartTemp(startTemp);
                    climate.setEndTemp(endTemp);
                    climate.setAverageTemperature(averageTempOverPeriod);
                    climate.setTempPercent(tempPercentage);
                    // Add the lga object to the array
                    climates.add(climate);
                }
            } else if (dataType.equals("Land-Ocean Data")) {
                // The Query
                String query = "SELECT SY.StartYear, ";
                query = query + "ROUND(SY.StartYearAvg, 2) AS StartYearAvg, ";
                query = query
                        + "EY.EndYear, ROUND(EY.EndYearAvg, 2) AS EndYearAvg, ROUND(AVG(ATOP.AvgOceanTemp), 2) AS AverageTempOverPeriod, ROUND(((EY.EndYearAvg - SY.StartYearAvg) / SY.StartYearAvg) * 100, 2) AS ChangeInAvgTempPercentage ";
                query = query
                        + "FROM (SELECT Year AS StartYear, AvgOceanTemp AS StartYearAvg FROM GlobalLandOceanTemp WHERE Year = '"
                        + startYear + "') AS SY ";
                query = query
                        + "JOIN (SELECT Year AS EndYear, AvgOceanTemp AS EndYearAvg FROM GlobalLandOceanTemp WHERE Year = '"
                        + endYear + "') AS EY JOIN GlobalLandOceanTemp AS ATOP ";
                query = query + "ON ATOP.Year BETWEEN SY.StartYear AND EY.EndYear GROUP BY SY.StartYear, EY.EndYear; ";

                // Get Result
                ResultSet results = statement.executeQuery(query);

                // Process all of the results
                while (results.next()) {
                    // Lookup the columns we need

                    float startTemp = results.getFloat("StartYearAvg");
                    float endTemp = results.getFloat("EndYearAvg");
                    float averageTempOverPeriod = results.getFloat("AverageTempOverPeriod");
                    float tempPercentage = results.getFloat("ChangeInAvgTempPercentage");

                    // Create a Climate Object
                    Climate climate = new Climate();
                    climate.setDataType(dataType);
                    climate.setStartTemp(startTemp);
                    climate.setEndTemp(endTemp);
                    climate.setAverageTemperature(averageTempOverPeriod);
                    climate.setTempPercent(tempPercentage);

                    // Add the lga object to the array
                    climates.add(climate);
                }
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

    public ArrayList<Climate> getPopulationYears() {
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
                    SELECT DISTINCT Year
                    FROM CountryPopulation;
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

        // Finally we return all of the lga
        return climates;
    
    }
    public static ArrayList<PersonaData> getPersonaData() {

        ArrayList<PersonaData> personaInfo = new ArrayList<PersonaData>();
 
 
        Connection connection = null;
 
        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);
 
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
 
            // The Query
            String query = "SELECT PersonaId, Name, Quote, ImagePath, Requirements, Experience FROM Persona;";
 
 
            // Get Result
            ResultSet results = statement.executeQuery(query);
 
            // Process all of the results
           while (results.next()) {
                // Lookup the columns we need
                 PersonaData personaData = new PersonaData(results.getInt("PersonaId"),
                         results.getString("Name"), results.getString("Quote"),
                         results.getString("ImagePath"), results.getString("Requirements"), results.getString("Experience"));
                 personaInfo.add(personaData);
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
         return personaInfo;
     }

public static ArrayList<StudentInfo> getStudentInfo() {

    ArrayList<StudentInfo> studentData = new ArrayList<StudentInfo>();


    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT StudentNumber, Fname, Lname, Email FROM StudentInfo;";


        // Get Result
        ResultSet results = statement.executeQuery(query);

        // Process all of the results
       while (results.next()) {
            // Lookup the columns we need
             StudentInfo studentInfo = new StudentInfo(results.getString("StudentNumber"),
                     results.getString("Fname"), results.getString("Lname"),
                     results.getString("Email"));
             studentData.add(studentInfo);
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
     return studentData;
 }
 public static boolean hasCities(String countryId) {


    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT count(*) as total from CityTemp where CountryId='" + countryId + "';";


        // Get Result
        ResultSet results = statement.executeQuery(query);

        // Process all of the results
       while (results.next()) {
            // Lookup the columns we need
             if (results.getInt("total") > 0){
                return true;
             }else{
                return false;
             }
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
     return false;
 }
 public static boolean hasStates(String countryId) {


    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT count(*) as total from StateTemp where CountryId='" + countryId + "';";


        // Get Result
        ResultSet results = statement.executeQuery(query);

        // Process all of the results
       while (results.next()) {
            // Lookup the columns we need
             if (results.getInt("total") > 0){
                return true;
             }else{
                return false;
             }
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
     return false;
 }
 public static ArrayList<String> getDistinctStateNames(String countryId, int fromDate, int toDate) {

    ArrayList<String> distinctStateName = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT DISTINCT StateName WHERE CountryId='" + countryId + "' AND Year >= " + fromDate + " and Year <= " + toDate + " AND AvgTemp IS NOT NULL, MinTemp IS NOT NULL, MaxTemp IS NOT NULL;";

        // Get Result
        ResultSet results = statement.executeQuery(query);

        // Process all of the results
        while (results.next()) {
            // Lookup the columns we need
            String distinctStateNames = new String(results.getString("StateName"));
                    distinctStateName.add(distinctStateNames);
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
    return distinctStateName;
}



public static ArrayList<String> distinctCityNames(String countryId, int fromDate, int toDate) {

   ArrayList<String> distinctCityName = new ArrayList<String>();


   Connection connection = null;

   try {
       // Connect to JDBC data base
       connection = DriverManager.getConnection(DATABASE);

       // Prepare a new SQL Query & Set a timeout
       Statement statement = connection.createStatement();
       statement.setQueryTimeout(30);

       // The Query
       String query = "SELECT DISTINCT CityName WHERE CountryId='" + countryId + "' AND Year > " + fromDate + " and Year < " + toDate + " ;";


       // Get Result
       ResultSet results = statement.executeQuery(query);

       // Process all of the results
      while (results.next()) {
           // Lookup the columns we need
            String distinctCityNames = new String(results.getString("CityName"));
                    distinctCityName.add(distinctCityNames);
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
    return distinctCityName;
}

public ArrayList<Climate> getCountryName() {
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

            String countryName = results.getString("CountryName");

            // Create a Climate Object
            Climate climate = new Climate();
            climate.setCountryName(countryName);

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
