package com.profilehost.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory profile store — replace with DB layer (JPA/Hibernate) in production.
 */
public class ProfileStore {

    private static final ProfileStore INSTANCE = new ProfileStore();
    private final Map<String, Profile> byId = new ConcurrentHashMap<>();
    private final Map<String, Profile> byUsername = new ConcurrentHashMap<>();

    private ProfileStore() {
        seedDemoProfiles();
    }

    public static ProfileStore getInstance() {
        return INSTANCE;
    }

    public List<Profile> getAll() {
        return new ArrayList<>(byId.values());
    }

    public Optional<Profile> findById(String id) {
        return Optional.ofNullable(byId.get(id));
    }

    public Optional<Profile> findByUsername(String username) {
        return Optional.ofNullable(byUsername.get(username.toLowerCase()));
    }

    public Profile save(Profile profile) {
        byId.put(profile.getId(), profile);
        byUsername.put(profile.getUsername().toLowerCase(), profile);
        return profile;
    }

    public boolean delete(String id) {
        Profile p = byId.remove(id);
        if (p != null) {
            byUsername.remove(p.getUsername().toLowerCase());
            return true;
        }
        return false;
    }

    public boolean usernameExists(String username) {
        return byUsername.containsKey(username.toLowerCase());
    }

    // ── Seed data ─────────────────────────────────────────────────────────────
    private void seedDemoProfiles() {
        // Profile 1
        Profile p1 = new Profile();
        p1.setUsername("shreeni");
        p1.setDisplayName("Shreeni Dev");
        p1.setTagline("Cloud & DevOps Engineer · Agentic AI Enthusiast");
        p1.setBio("Building the future of infrastructure automation at the intersection of AI, cloud, and DevOps. Passionate about agentic systems, CI/CD pipelines, and making deployments delightfully boring.");
        p1.setRole("Senior DevOps Engineer");
        p1.setCompany("CorroHealth");
        p1.setLocation("Bengaluru, India");
        p1.setWebsite("https://shreeni.dev");
        p1.setEmail("shreeni@example.com");
        p1.setCoverColor("#0f172a");
        p1.setTheme("dark");
        p1.setAvatarUrl("https://api.dicebear.com/7.x/bottts-neutral/svg?seed=shreeni&backgroundColor=0f172a");
        p1.setSkills(Arrays.asList("Azure DevOps", "LangChain", "AWS", "Python", "Java", "Docker", "Kubernetes", "Flyway", "CI/CD", "LangGraph"));
        p1.setSocialLinks(Arrays.asList(
            new Profile.SocialLink("GitHub", "https://github.com/shreeni", "github"),
            new Profile.SocialLink("LinkedIn", "https://linkedin.com/in/shreeni", "linkedin"),
            new Profile.SocialLink("Twitter", "https://twitter.com/shreeni", "twitter")
        ));
        p1.setProjects(Arrays.asList(
            new Profile.Project("ACS Flyway Pipeline", "Enterprise CI/CD pipeline for database schema management with multi-env support & approval gates", "#", "Azure DevOps · Flyway · PowerShell", "live"),
            new Profile.Project("AWS Two-Tier IaC", "CloudFormation template for ALB + Auto Scaling EC2 + RDS MySQL across multiple AZs", "#", "AWS · CloudFormation · RDS", "live"),
            new Profile.Project("Agentic LLM Toolkit", "Day-1 curriculum: context management with LangChain + Google Generative AI", "#", "Python · LangChain · LangGraph", "wip")
        ));
        p1.setViews(2847);
        save(p1);

        // Profile 2
        Profile p2 = new Profile();
        p2.setUsername("akira");
        p2.setDisplayName("Akira Tanaka");
        p2.setTagline("Full-Stack Engineer · Open Source Contributor");
        p2.setBio("I craft delightful user experiences with React and ship robust backends with Spring Boot. Coffee-driven, docs-first developer who believes great software is poetry.");
        p2.setRole("Software Engineer");
        p2.setCompany("Stripe");
        p2.setLocation("Tokyo, Japan");
        p2.setWebsite("https://akira.codes");
        p2.setEmail("akira@example.com");
        p2.setCoverColor("#1a1a2e");
        p2.setTheme("neon");
        p2.setAvatarUrl("https://api.dicebear.com/7.x/bottts-neutral/svg?seed=akira&backgroundColor=1a1a2e");
        p2.setSkills(Arrays.asList("React", "TypeScript", "Spring Boot", "PostgreSQL", "Redis", "GraphQL", "Docker"));
        p2.setSocialLinks(Arrays.asList(
            new Profile.SocialLink("GitHub", "https://github.com/akira", "github"),
            new Profile.SocialLink("Twitter", "https://twitter.com/akira", "twitter")
        ));
        p2.setProjects(Arrays.asList(
            new Profile.Project("OpenAPI Studio", "Visual API design tool with real-time collaboration", "#", "React · TypeScript · WebSockets", "live"),
            new Profile.Project("Spring Starter Kit", "Production-grade Spring Boot template with auth, logging, and observability", "#", "Java · Spring Boot · Micrometer", "live")
        ));
        p2.setViews(1203);
        save(p2);

        // Profile 3
        Profile p3 = new Profile();
        p3.setUsername("maya");
        p3.setDisplayName("Maya Chen");
        p3.setTagline("ML Engineer · Data Storyteller");
        p3.setBio("Turning raw data into actionable insights. I specialize in NLP, recommendation systems, and MLOps pipelines. Former research scientist turned industry builder.");
        p3.setRole("ML Engineer");
        p3.setCompany("Cohere");
        p3.setLocation("San Francisco, CA");
        p3.setWebsite("https://mayachen.ai");
        p3.setEmail("maya@example.com");
        p3.setCoverColor("#064e3b");
        p3.setTheme("light");
        p3.setAvatarUrl("https://api.dicebear.com/7.x/bottts-neutral/svg?seed=maya&backgroundColor=064e3b");
        p3.setSkills(Arrays.asList("Python", "PyTorch", "Transformers", "MLflow", "Kubernetes", "SQL", "Scala", "Spark"));
        p3.setSocialLinks(Arrays.asList(
            new Profile.SocialLink("GitHub", "https://github.com/mayachen", "github"),
            new Profile.SocialLink("LinkedIn", "https://linkedin.com/in/mayachen", "linkedin")
        ));
        p3.setProjects(Arrays.asList(
            new Profile.Project("RecSys Engine", "Collaborative filtering + content-based hybrid recommender at 10M+ scale", "#", "PyTorch · Redis · FastAPI", "live"),
            new Profile.Project("NLP Pipeline", "End-to-end document classification and entity extraction pipeline", "#", "HuggingFace · Airflow · PostgreSQL", "live"),
            new Profile.Project("ML Observability", "Drift detection and model monitoring dashboard", "#", "Evidently · Grafana · Python", "wip")
        ));
        p3.setViews(4512);
        save(p3);
    }
}
