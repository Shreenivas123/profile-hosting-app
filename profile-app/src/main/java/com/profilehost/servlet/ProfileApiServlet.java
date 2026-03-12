package com.profilehost.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.profilehost.model.Profile;
import com.profilehost.model.ProfileStore;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST-ish JSON API for profile data.
 * GET /api/profiles        → all profiles
 * GET /api/profiles/{id}   → single profile by ID
 */
@WebServlet("/api/profiles/*")
public class ProfileApiServlet extends HttpServlet {

    private ObjectMapper mapper;

    @Override
    public void init() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json;charset=UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");

        String pathInfo = req.getPathInfo();

        if (pathInfo == null || pathInfo.equals("/")) {
            List<Profile> profiles = ProfileStore.getInstance().getAll();
            mapper.writeValue(resp.getWriter(), Map.of(
                    "success", true,
                    "count", profiles.size(),
                    "data", profiles
            ));
        } else {
            String id = pathInfo.substring(1);
            Optional<Profile> profile = ProfileStore.getInstance().findById(id);
            if (profile.isPresent()) {
                mapper.writeValue(resp.getWriter(), Map.of("success", true, "data", profile.get()));
            } else {
                resp.setStatus(404);
                mapper.writeValue(resp.getWriter(), Map.of("success", false, "error", "Profile not found"));
            }
        }
    }
}
