package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    public QuantityMeasurementDatabaseRepository() {

        createTableIfNotExists();
    }

    private void createTableIfNotExists() {

        String query = """
                CREATE TABLE IF NOT EXISTS quantity_measurements (
                
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    
                    operation_type VARCHAR(50),
                    measurement_type VARCHAR(50),
                    
                    value1 DOUBLE,
                    unit1 VARCHAR(50),
                    
                    value2 DOUBLE,
                    unit2 VARCHAR(50),
                    
                    result VARCHAR(255)
                )
                """;

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.execute(query);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void save(
            QuantityMeasurementEntity entity
    ) {

        String query = """
                INSERT INTO quantity_measurements
                (
                    operation_type,
                    measurement_type,
                    value1,
                    unit1,
                    value2,
                    unit2,
                    result
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                PreparedStatement preparedStatement =
                        connection.prepareStatement(query)
        ) {

            preparedStatement.setString(
                    1,
                    entity.getOperationType()
            );

            preparedStatement.setString(
                    2,
                    entity.getMeasurementType()
            );

            preparedStatement.setDouble(
                    3,
                    entity.getValue1()
            );

            preparedStatement.setString(
                    4,
                    entity.getUnit1()
            );

            preparedStatement.setDouble(
                    5,
                    entity.getValue2()
            );

            preparedStatement.setString(
                    6,
                    entity.getUnit2()
            );

            preparedStatement.setString(
                    7,
                    entity.getResult()
            );

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Database Save Failed"
            );
        }
    }

    @Override
    public List<QuantityMeasurementEntity>
    getAllMeasurements() {

        List<QuantityMeasurementEntity> list =
                new ArrayList<>();

        String query =
                "SELECT * FROM quantity_measurements";

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(query)
        ) {

            while (resultSet.next()) {

                QuantityMeasurementEntity entity =
                        new QuantityMeasurementEntity();

                entity.setOperationType(
                        resultSet.getString(
                                "operation_type"
                        )
                );

                entity.setMeasurementType(
                        resultSet.getString(
                                "measurement_type"
                        )
                );

                entity.setValue1(
                        resultSet.getDouble(
                                "value1"
                        )
                );

                entity.setUnit1(
                        resultSet.getString(
                                "unit1"
                        )
                );

                entity.setValue2(
                        resultSet.getDouble(
                                "value2"
                        )
                );

                entity.setUnit2(
                        resultSet.getString(
                                "unit2"
                        )
                );

                entity.setResult(
                        resultSet.getString(
                                "result"
                        )
                );

                list.add(entity);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteAllMeasurements() {

        String query =
                "DELETE FROM quantity_measurements";

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement()
        ) {

            statement.executeUpdate(query);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @Override
    public long getTotalCount() {

        String query =
                "SELECT COUNT(*) FROM quantity_measurements";

        try (
                Connection connection =
                        ConnectionPool.getConnection();

                Statement statement =
                        connection.createStatement();

                ResultSet resultSet =
                        statement.executeQuery(query)
        ) {

            if (resultSet.next()) {

                return resultSet.getLong(1);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return 0;
    }
}