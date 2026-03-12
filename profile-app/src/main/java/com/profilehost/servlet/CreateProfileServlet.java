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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/create")
public class CreateProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String error = req.getParameter("error");
        resp.getWriter().println(HtmlTemplates.buildCreatePage(error));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = sanitize(req.getParameter("username"));
        String displayName = sanitize(req.getParameter("displayName"));
        String tagline = sanitize(req.getParameter("tagline"));
        String bio = sanitize(req.getParameter("bio"));
        String role = sanitize(req.getParameter("role"));
        String company = sanitize(req.getParameter("company"));
        String location = sanitize(req.getParameter("location"));
        String website = sanitize(req.getParameter("website"));
        String email = sanitize(req.getParameter("email"));
        String skillsRaw = sanitize(req.getParameter("skills"));
        String theme = sanitize(req.getParameter("theme"));
        String coverColor = sanitize(req.getParameter("coverColor"));

        // Validation
        if (username == null || username.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/create?error=Username+is+required");
            return;
        }
        if (!username.matches("[a-zA-Z0-9_-]{3,30}")) {
            resp.sendRedirect(req.getContextPath() + "/create?error=Username+must+be+3-30+alphanumeric+chars");
            return;
        }
        if (ProfileStore.getInstance().usernameExists(username)) {
            resp.sendRedirect(req.getContextPath() + "/create?error=Username+already+taken");
            return;
        }

        Profile profile = new Profile();
        profile.setUsername(username);
        profile.setDisplayName(displayName != null ? displayName : username);
        profile.setTagline(tagline);
        profile.setBio(bio);
        profile.setRole(role);
        profile.setCompany(company);
        profile.setLocation(location);
        profile.setWebsite(website);
        profile.setEmail(email);
        profile.setTheme(theme != null ? theme : "dark");
        profile.setCoverColor(coverColor != null ? coverColor : "#6366f1");
        profile.setAvatarUrl("https://api.dicebear.com/7.x/bottts-neutral/svg?seed=" + username + "&backgroundColor=" + coverColor.replace("#", ""));

        if (skillsRaw != null && !skillsRaw.isBlank()) {
            List<String> skills = Arrays.stream(skillsRaw.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isBlank())
                    .collect(Collectors.toList());
            profile.setSkills(skills);
        }

        ProfileStore.getInstance().save(profile);
        resp.sendRedirect(req.getContextPath() + "/p/" + username);
    }

    private String sanitize(String input) {
        if (input == null) return null;
        return input.trim()
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
