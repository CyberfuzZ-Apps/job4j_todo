package ru.job4j.todo.controller;

import ru.job4j.todo.storage.HbmStorage;
import ru.job4j.todo.storage.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс UpdateItemServlet
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
public class UpdateItemServlet extends HttpServlet {

    private final Store store = new HbmStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        store.update(id);
    }
}
