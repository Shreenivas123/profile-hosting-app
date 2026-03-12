package com.profilehost.servlet;

import com.profilehost.model.Profile;
import com.profilehost.model.ProfileStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"", "/", "/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        List<Profile> profiles = ProfileStore.getInstance().getAll();

        PrintWriter out = resp.getWriter();
        out.println(HtmlTemplates.buildHomePage(profiles));
    }
}
