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
import java.util.Optional;

@WebServlet("/p/*")
public class ProfileViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        String username = pathInfo.substring(1); // remove leading slash
        Optional<Profile> profileOpt = ProfileStore.getInstance().findByUsername(username);

        if (profileOpt.isEmpty()) {
            resp.setStatus(404);
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().println(HtmlTemplates.build404Page(username));
            return;
        }

        Profile profile = profileOpt.get();
        // Increment view count
        profile.setViews(profile.getViews() + 1);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(HtmlTemplates.buildProfilePage(profile));
    }
}
