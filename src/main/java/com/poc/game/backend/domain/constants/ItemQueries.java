package com.poc.game.domain.constants;

public class ItemQueries {
    public static final String QUERY_SELECT_ITEMS = "SELECT * FROM items ORDER BY value;";
    public static final String QUERY_INSERT_ITEM = "INSERT INTO items (value,name) VALUES (?,?);";
    public static final String QUERY_SELECT_ITEM_BY_ID = "SELECT * from items WHERE id = ?;";
    public static final String QUERY_DELETE_ITEM = "DELETE FROM items WHERE id = ?;";
}
