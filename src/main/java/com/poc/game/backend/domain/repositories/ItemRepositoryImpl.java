package com.poc.game.backend.domain.repositories;

import com.poc.game.backend.domain.constants.ItemQueries;
import com.poc.game.backend.domain.dtos.CreateItemDTO;
import com.poc.game.backend.domain.entities.Item;
import com.poc.game.backend.infra.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    private final Database database;

    public ItemRepositoryImpl(){
        this.database =  Database.getInstance();
    }

    @Override
    public List<Item> findAll() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Item> items = new ArrayList<>();

        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(ItemQueries.QUERY_SELECT_ITEMS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setValue(resultSet.getInt("value"));
                item.setName(resultSet.getString("name"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeConnection(connection);
        }

        return items;
    }

    @Override
    public void create(CreateItemDTO createItemDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(ItemQueries.QUERY_INSERT_ITEM);

            preparedStatement.setInt(1, createItemDTO.getValue()); // value
            preparedStatement.setString(2,createItemDTO.getName()); // name

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            database.closeResources(preparedStatement, null);
            database.closeConnection(connection);
        }
    }

    @Override
    public Item findById(int itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Item item = null;

        try {
            connection = database.getConnection();

            preparedStatement = connection.prepareStatement(ItemQueries.QUERY_SELECT_ITEM_BY_ID);

            // Define o valor do parâmetro na consulta
            preparedStatement.setInt(1, itemId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Mapeie o resultado para um objeto Item
                item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setValue(resultSet.getInt("value"));
                item.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            // Trate exceções apropriadas aqui
            e.printStackTrace();
        } finally {
            Database.getInstance().closeResources(preparedStatement, resultSet);
            Database.getInstance().closeConnection(connection);
        }

        return item;
    }

    @Override
    public void delete(int itemId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = database.getConnection();
            preparedStatement = connection.prepareStatement(ItemQueries.QUERY_DELETE_ITEM);

            // Define o valor do parâmetro na consulta
            preparedStatement.setInt(1, itemId);

            // Executa a consulta de exclusão
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Trate exceções apropriadas aqui
            e.printStackTrace();
        } finally {
            database.closeResources(preparedStatement, null); // Não há ResultSet nesta operação
            database.closeConnection(connection);
        }
    }
}
