package com.profilehost.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile {
    private String id;
    private String username;
    private String displayName;
    private String tagline;
    private String bio;
    private String avatarUrl;
    private String coverColor;
    private String location;
    private String website;
    private String email;
    private String role;
    private String company;
    private List<String> skills;
    private List<SocialLink> socialLinks;
    private List<Project> projects;
    private LocalDateTime createdAt;
    private int views;
    private String theme; // "dark", "light", "neon", "minimal"

    public Profile() {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.skills = new ArrayList<>();
        this.socialLinks = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.views = 0;
        this.coverColor = "#6366f1";
        this.theme = "dark";
    }

    public static class SocialLink {
        private String platform;
        private String url;
        private String icon;

        public SocialLink(String platform, String url, String icon) {
            this.platform = platform;
            this.url = url;
            this.icon = icon;
        }

        public String getPlatform() { return platform; }
        public String getUrl() { return url; }
        public String getIcon() { return icon; }
        public void setPlatform(String platform) { this.platform = platform; }
        public void setUrl(String url) { this.url = url; }
        public void setIcon(String icon) { this.icon = icon; }
    }

    public static class Project {
        private String title;
        private String description;
        private String url;
        private String tech;
        private String status; // "live", "wip", "archived"

        public Project(String title, String description, String url, String tech, String status) {
            this.title = title;
            this.description = description;
            this.url = url;
            this.tech = tech;
            this.status = status;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getUrl() { return url; }
        public String getTech() { return tech; }
        public String getStatus() { return status; }
        public void setTitle(String title) { this.title = title; }
        public void setDescription(String description) { this.description = description; }
        public void setUrl(String url) { this.url = url; }
        public void setTech(String tech) { this.tech = tech; }
        public void setStatus(String status) { this.status = status; }
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getTagline() { return tagline; }
    public void setTagline(String tagline) { this.tagline = tagline; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public String getCoverColor() { return coverColor; }
    public void setCoverColor(String coverColor) { this.coverColor = coverColor; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }
    public List<SocialLink> getSocialLinks() { return socialLinks; }
    public void setSocialLinks(List<SocialLink> socialLinks) { this.socialLinks = socialLinks; }
    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public int getViews() { return views; }
    public void setViews(int views) { this.views = views; }
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
}
