package ru.job4j.todo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.model.Category;
import ru.job4j.todo.storage.HbmStorage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Класс CategoryServlet - сервлет получения списка всех категорий из БД.
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class CategoryServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Category> categoryList = (List<Category>) HbmStorage.instOf().findAllCategories();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        OutputStream output = resp.getOutputStream();
        String json = GSON.toJson(categoryList);
        output.write(json.getBytes(StandardCharsets.UTF_8));
        output.flush();
        output.close();
    }
}
