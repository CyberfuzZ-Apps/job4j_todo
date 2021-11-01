package ru.job4j.todo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.storage.HbmStorage;
import ru.job4j.todo.storage.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Класс ItemServlet
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class ItemServlet extends HttpServlet {

    private final Store store = new HbmStorage();
    private static final Gson GSON = new GsonBuilder().create();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> itemList = (List<Item>) store.findAll();
        if (!"true".equals(req.getParameter("show_all"))) {
            itemList.removeIf(Item::isDone);
        }
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(itemList);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String description = req.getParameter("description");
        Item item = new Item(
                0,
                description,
                new Timestamp(new Date().getTime()),
                false
        );
        store.save(item);
    }

}
