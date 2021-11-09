package ru.job4j.todo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Item;
import ru.job4j.todo.model.User;
import ru.job4j.todo.storage.HbmStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Класс ItemServlet - сервлет получения списка заявок и их добавления в бд.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class ItemServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Item> itemList = (List<Item>) HbmStorage.instOf().findAll();
        boolean showAll = Boolean.parseBoolean(req.getParameter("show_all"));
        if (!showAll) {
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
        String categoriesStr = req.getParameter("categories");
        String[] categories = categoriesStr.substring(0, categoriesStr.length() - 1).split(",");
        HttpSession sc = req.getSession();
        User user = (User) sc.getAttribute("user");
        Item item = new Item(
                0,
                description,
                new Timestamp(new Date().getTime()),
                false,
                user
        );
        HbmStorage.instOf().save(item, categories);
    }

}
