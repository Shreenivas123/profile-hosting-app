package com.profilehost.servlet;

import com.profilehost.model.Profile;
import java.util.List;

/**
 * HTML templates for all pages.
 * Design: Editorial dark luxury — ink-black base, champagne gold accents,
 * Playfair Display headings, JetBrains Mono for code elements, animated gradients.
 */
public class HtmlTemplates {

    // ──────────────────────────────────────────────────────────────────────────
    // SHARED HEAD / STYLES
    // ──────────────────────────────────────────────────────────────────────────
    private static String head(String title) {
        return """
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title>%s · ProfileHost</title>
<link rel="preconnect" href="https://fonts.googleapis.com"/>
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:ital,wght@0,400;0,700;0,900;1,400&family=DM+Sans:wght@300;400;500;600&family=JetBrains+Mono:wght@400;600&display=swap" rel="stylesheet"/>
<style>
*,*::before,*::after{box-sizing:border-box;margin:0;padding:0}
:root{
  --ink:#080a0e;
  --ink2:#0f1117;
  --ink3:#161b27;
  --ink4:#1e2535;
  --border:#ffffff0f;
  --border2:#ffffff18;
  --text:#e8e6e0;
  --text-muted:#7a7870;
  --text-dim:#a09e98;
  --gold:#c9a94e;
  --gold2:#e8c56a;
  --gold-glow:rgba(201,169,78,0.18);
  --accent:#4f8ef7;
  --accent2:#7eb8ff;
  --green:#2ddb76;
  --red:#ff5c5c;
  --radius:12px;
  --radius-lg:20px;
  --shadow:0 8px 40px rgba(0,0,0,0.5);
  --shadow-gold:0 0 40px rgba(201,169,78,0.12);
}
html{scroll-behavior:smooth}
body{
  background:var(--ink);
  color:var(--text);
  font-family:'DM Sans',sans-serif;
  font-size:15px;
  line-height:1.65;
  min-height:100vh;
  overflow-x:hidden;
}

/* ── NOISE GRAIN OVERLAY ── */
body::before{
  content:'';
  position:fixed;inset:0;
  background-image:url("data:image/svg+xml,%3Csvg viewBox='0 0 256 256' xmlns='http://www.w3.org/2000/svg'%3E%3Cfilter id='noise'%3E%3CfeTurbulence type='fractalNoise' baseFrequency='0.9' numOctaves='4' stitchTiles='stitch'/%3E%3C/filter%3E%3Crect width='100%25' height='100%25' filter='url(%23noise)' opacity='0.04'/%3E%3C/svg%3E");
  pointer-events:none;z-index:9999;opacity:0.5;
}

/* ── NAVBAR ── */
nav{
  position:sticky;top:0;z-index:100;
  display:flex;align-items:center;justify-content:space-between;
  padding:0 2.5rem;height:64px;
  background:rgba(8,10,14,0.88);
  backdrop-filter:blur(24px) saturate(1.5);
  border-bottom:1px solid var(--border);
}
.nav-logo{
  font-family:'Playfair Display',serif;
  font-size:1.3rem;font-weight:700;
  color:var(--gold);
  letter-spacing:0.02em;
  text-decoration:none;
}
.nav-logo span{color:var(--text-dim);font-weight:400}
.nav-links{display:flex;gap:0.5rem;align-items:center}
.nav-links a{
  color:var(--text-dim);text-decoration:none;
  padding:0.45rem 1rem;border-radius:8px;
  font-size:0.88rem;font-weight:500;
  transition:all 0.2s;
}
.nav-links a:hover{color:var(--text);background:var(--ink3)}
.btn-primary{
  background:linear-gradient(135deg,var(--gold),var(--gold2)) !important;
  color:var(--ink) !important;
  font-weight:600 !important;
  padding:0.45rem 1.25rem !important;
  border-radius:8px !important;
  transition:all 0.2s !important;
}
.btn-primary:hover{transform:translateY(-1px);box-shadow:0 4px 20px var(--gold-glow) !important}

/* ── HERO ── */
.hero{
  position:relative;
  padding:6rem 2.5rem 4rem;
  text-align:center;
  overflow:hidden;
}
.hero::before{
  content:'';position:absolute;inset:0;
  background:radial-gradient(ellipse 80% 60% at 50% 0%, rgba(201,169,78,0.08) 0%, transparent 70%),
              radial-gradient(ellipse 60% 40% at 80% 100%, rgba(79,142,247,0.06) 0%, transparent 70%);
  pointer-events:none;
}
.hero-eyebrow{
  display:inline-flex;align-items:center;gap:0.5rem;
  background:var(--ink3);border:1px solid var(--border2);
  padding:0.35rem 1rem;border-radius:100px;
  font-family:'JetBrains Mono',monospace;
  font-size:0.75rem;color:var(--gold);letter-spacing:0.06em;
  margin-bottom:1.5rem;
}
.hero-eyebrow::before{
  content:'';width:6px;height:6px;border-radius:50%;
  background:var(--green);
  animation:pulse 2s ease-in-out infinite;
}
@keyframes pulse{0%,100%{opacity:1;transform:scale(1)}50%{opacity:0.4;transform:scale(0.8)}}
.hero h1{
  font-family:'Playfair Display',serif;
  font-size:clamp(2.5rem,6vw,4.5rem);
  font-weight:900;line-height:1.1;
  color:var(--text);
  margin-bottom:1rem;
}
.hero h1 em{
  font-style:italic;
  background:linear-gradient(135deg,var(--gold),var(--gold2));
  -webkit-background-clip:text;-webkit-text-fill-color:transparent;
  background-clip:text;
}
.hero-sub{
  font-size:1.1rem;color:var(--text-dim);
  max-width:540px;margin:0 auto 2.5rem;
  font-weight:300;
}
.hero-cta{display:flex;gap:1rem;justify-content:center;flex-wrap:wrap}
.cta-btn{
  display:inline-flex;align-items:center;gap:0.5rem;
  padding:0.8rem 2rem;border-radius:10px;
  font-size:0.95rem;font-weight:600;text-decoration:none;
  transition:all 0.25s;cursor:pointer;border:none;
}
.cta-primary{
  background:linear-gradient(135deg,var(--gold),var(--gold2));
  color:var(--ink);
}
.cta-primary:hover{transform:translateY(-2px);box-shadow:0 8px 32px var(--gold-glow)}
.cta-secondary{
  background:transparent;color:var(--text);
  border:1px solid var(--border2);
}
.cta-secondary:hover{background:var(--ink3);border-color:var(--border);transform:translateY(-2px)}

/* ── STATS BAR ── */
.stats-bar{
  display:flex;justify-content:center;gap:3rem;
  padding:2rem;margin:0 2.5rem;
  background:var(--ink2);border:1px solid var(--border);
  border-radius:var(--radius-lg);
}
.stat{text-align:center}
.stat-n{
  font-family:'Playfair Display',serif;font-size:2rem;font-weight:700;
  color:var(--gold);display:block;
}
.stat-l{font-size:0.8rem;color:var(--text-muted);letter-spacing:0.05em;text-transform:uppercase}

/* ── SECTION ── */
.section{padding:4rem 2.5rem}
.section-header{
  display:flex;align-items:center;justify-content:space-between;
  margin-bottom:2.5rem;
}
.section-title{
  font-family:'Playfair Display',serif;
  font-size:1.8rem;font-weight:700;color:var(--text);
}
.section-title span{
  color:var(--gold);
}

/* ── PROFILE GRID ── */
.profile-grid{
  display:grid;
  grid-template-columns:repeat(auto-fill,minmax(320px,1fr));
  gap:1.5rem;
}
.profile-card{
  background:var(--ink2);
  border:1px solid var(--border);
  border-radius:var(--radius-lg);
  overflow:hidden;
  transition:all 0.3s;
  cursor:pointer;
  text-decoration:none;
  display:block;
  position:relative;
}
.profile-card:hover{
  transform:translateY(-4px);
  border-color:var(--border2);
  box-shadow:0 20px 60px rgba(0,0,0,0.4),0 0 0 1px rgba(201,169,78,0.1);
}
.card-cover{
  height:100px;
  position:relative;
}
.card-cover-inner{
  position:absolute;inset:0;
  opacity:0.8;
}
.card-body{padding:0 1.5rem 1.5rem}
.card-avatar{
  width:72px;height:72px;border-radius:50%;
  border:3px solid var(--ink2);
  margin-top:-36px;
  position:relative;z-index:1;
  background:var(--ink3);
  overflow:hidden;
}
.card-avatar img{width:100%;height:100%;object-fit:cover}
.card-name{
  font-family:'Playfair Display',serif;
  font-size:1.2rem;font-weight:700;
  color:var(--text);margin-top:0.75rem;
}
.card-tagline{
  font-size:0.83rem;color:var(--text-dim);
  margin-top:0.25rem;
  display:-webkit-box;-webkit-line-clamp:2;
  -webkit-box-orient:vertical;overflow:hidden;
}
.card-meta{
  display:flex;align-items:center;gap:1rem;
  margin-top:1rem;
  font-size:0.78rem;color:var(--text-muted);
}
.card-meta span{display:flex;align-items:center;gap:0.3rem}
.skills-row{
  display:flex;flex-wrap:wrap;gap:0.4rem;margin-top:1rem;
}
.skill-tag{
  background:var(--ink3);border:1px solid var(--border);
  color:var(--text-dim);
  font-family:'JetBrains Mono',monospace;
  font-size:0.7rem;padding:0.2rem 0.55rem;border-radius:4px;
}

/* ── PROFILE PAGE ── */
.profile-hero{
  position:relative;
  height:260px;
  overflow:hidden;
}
.profile-cover{
  position:absolute;inset:0;
}
.profile-cover-gradient{
  position:absolute;bottom:0;left:0;right:0;height:60%;
  background:linear-gradient(to top,var(--ink) 0%,transparent 100%);
}
.profile-main{
  max-width:860px;margin:0 auto;
  padding:0 2rem 4rem;
}
.profile-avatar-wrap{
  position:relative;
  margin-top:-56px;margin-bottom:1.5rem;
}
.profile-avatar{
  width:110px;height:110px;border-radius:50%;
  border:4px solid var(--ink);
  background:var(--ink3);overflow:hidden;
  box-shadow:0 8px 32px rgba(0,0,0,0.6);
}
.profile-avatar img{width:100%;height:100%;object-fit:cover}
.profile-name{
  font-family:'Playfair Display',serif;
  font-size:2.5rem;font-weight:900;color:var(--text);
  line-height:1.1;
}
.profile-tagline{
  font-size:1rem;color:var(--gold);font-weight:500;
  margin-top:0.5rem;
}
.profile-meta-row{
  display:flex;flex-wrap:wrap;gap:1.2rem;
  margin-top:1rem;font-size:0.85rem;color:var(--text-dim);
}
.profile-meta-row span{display:flex;align-items:center;gap:0.4rem}

.profile-grid2{
  display:grid;grid-template-columns:1fr 300px;gap:2rem;
  margin-top:2.5rem;
}
@media(max-width:700px){
  .profile-grid2{grid-template-columns:1fr}
  .profile-hero{height:180px}
  .profile-name{font-size:1.8rem}
  nav{padding:0 1rem}
  .hero{padding:4rem 1.5rem 3rem}
  .section{padding:3rem 1.5rem}
  .stats-bar{flex-wrap:wrap;gap:1.5rem;margin:0 1rem}
}

.card-block{
  background:var(--ink2);border:1px solid var(--border);
  border-radius:var(--radius);padding:1.5rem;
  margin-bottom:1.5rem;
}
.block-title{
  font-family:'Playfair Display',serif;
  font-size:1rem;font-weight:700;color:var(--text);
  margin-bottom:1rem;
  padding-bottom:0.75rem;border-bottom:1px solid var(--border);
  display:flex;align-items:center;gap:0.5rem;
}
.block-title::before{
  content:'';width:3px;height:16px;
  background:linear-gradient(to bottom,var(--gold),transparent);
  border-radius:2px;
}

.bio-text{
  font-size:0.93rem;color:var(--text-dim);
  line-height:1.75;
}

/* Skills */
.skills-list{display:flex;flex-wrap:wrap;gap:0.5rem}
.skill-pill{
  background:var(--ink3);border:1px solid var(--border2);
  color:var(--text);
  font-family:'JetBrains Mono',monospace;
  font-size:0.72rem;padding:0.3rem 0.75rem;
  border-radius:6px;
  transition:all 0.2s;
}
.skill-pill:hover{border-color:var(--gold);color:var(--gold)}

/* Projects */
.project-card{
  background:var(--ink3);border:1px solid var(--border);
  border-radius:var(--radius);padding:1.2rem;
  margin-bottom:0.75rem;
  transition:all 0.2s;
}
.project-card:hover{border-color:var(--border2)}
.project-title{font-weight:600;color:var(--text);font-size:0.95rem}
.project-desc{font-size:0.82rem;color:var(--text-dim);margin-top:0.4rem;line-height:1.6}
.project-tech{
  font-family:'JetBrains Mono',monospace;
  font-size:0.68rem;color:var(--text-muted);
  margin-top:0.6rem;
}
.status-badge{
  display:inline-flex;align-items:center;gap:0.3rem;
  font-size:0.68rem;padding:0.2rem 0.6rem;border-radius:100px;
  font-weight:600;
}
.status-live{background:rgba(45,219,118,0.12);color:var(--green)}
.status-wip{background:rgba(201,169,78,0.12);color:var(--gold)}
.status-archived{background:var(--ink4);color:var(--text-muted)}

/* Social */
.social-link{
  display:flex;align-items:center;gap:0.75rem;
  padding:0.6rem 0.75rem;border-radius:8px;
  color:var(--text-dim);text-decoration:none;
  font-size:0.88rem;
  transition:all 0.2s;margin-bottom:0.25rem;
}
.social-link:hover{background:var(--ink3);color:var(--text)}
.social-icon{
  width:28px;height:28px;
  background:var(--ink3);border-radius:6px;
  display:flex;align-items:center;justify-content:center;
  font-size:0.75rem;color:var(--gold);
  font-family:'JetBrains Mono',monospace;
}

/* VIEW COUNTER */
.view-counter{
  display:inline-flex;align-items:center;gap:0.4rem;
  background:var(--ink3);border:1px solid var(--border);
  padding:0.3rem 0.8rem;border-radius:100px;
  font-family:'JetBrains Mono',monospace;
  font-size:0.72rem;color:var(--text-muted);
}

/* ── CREATE FORM ── */
.create-wrap{max-width:680px;margin:0 auto;padding:3rem 2rem 6rem}
.form-title{
  font-family:'Playfair Display',serif;
  font-size:2.5rem;font-weight:900;
  color:var(--text);margin-bottom:0.5rem;
}
.form-sub{color:var(--text-dim);font-size:0.93rem;margin-bottom:2.5rem}
.form-section{margin-bottom:2rem}
.form-section-label{
  font-size:0.72rem;letter-spacing:0.1em;text-transform:uppercase;
  color:var(--text-muted);font-weight:600;
  margin-bottom:1rem;
  padding-bottom:0.6rem;border-bottom:1px solid var(--border);
}
.form-row{display:grid;grid-template-columns:1fr 1fr;gap:1rem;margin-bottom:1rem}
.form-group{margin-bottom:1rem}
label{
  display:block;font-size:0.82rem;color:var(--text-dim);
  font-weight:500;margin-bottom:0.4rem;
}
input,textarea,select{
  width:100%;background:var(--ink2);
  border:1px solid var(--border2);
  color:var(--text);border-radius:8px;
  padding:0.7rem 1rem;font-size:0.9rem;
  font-family:'DM Sans',sans-serif;
  transition:all 0.2s;outline:none;
}
input:focus,textarea:focus,select:focus{
  border-color:var(--gold);
  box-shadow:0 0 0 3px rgba(201,169,78,0.1);
}
input::placeholder,textarea::placeholder{color:var(--text-muted)}
textarea{resize:vertical;min-height:100px}
select option{background:var(--ink2)}
.color-row{display:flex;align-items:center;gap:0.75rem}
input[type="color"]{
  width:48px;height:42px;padding:4px;cursor:pointer;
  border-radius:8px;
}
.submit-btn{
  width:100%;padding:1rem;
  background:linear-gradient(135deg,var(--gold),var(--gold2));
  color:var(--ink);font-weight:700;font-size:1rem;
  border:none;border-radius:10px;cursor:pointer;
  font-family:'DM Sans',sans-serif;
  transition:all 0.25s;margin-top:1rem;
}
.submit-btn:hover{transform:translateY(-2px);box-shadow:0 8px 32px var(--gold-glow)}
.error-banner{
  background:rgba(255,92,92,0.1);border:1px solid rgba(255,92,92,0.3);
  color:var(--red);padding:0.75rem 1rem;border-radius:8px;
  margin-bottom:1.5rem;font-size:0.88rem;
}
.tip{font-size:0.75rem;color:var(--text-muted);margin-top:0.3rem}

/* ── FOOTER ── */
footer{
  text-align:center;padding:3rem 2rem;
  border-top:1px solid var(--border);
  color:var(--text-muted);font-size:0.82rem;
}
footer a{color:var(--gold);text-decoration:none}
.footer-logo{
  font-family:'Playfair Display',serif;
  font-size:1.1rem;color:var(--gold);
  display:block;margin-bottom:0.5rem;
}

/* ANIMATIONS */
@keyframes fadeUp{from{opacity:0;transform:translateY(20px)}to{opacity:1;transform:translateY(0)}}
.fade-up{animation:fadeUp 0.6s ease forwards}
.fade-up-2{animation:fadeUp 0.6s 0.1s ease forwards;opacity:0}
.fade-up-3{animation:fadeUp 0.6s 0.2s ease forwards;opacity:0}
.fade-up-4{animation:fadeUp 0.6s 0.3s ease forwards;opacity:0}
</style>
</head>
<body>
""".formatted(title);
    }

    private static String nav() {
        return """
<nav>
  <a class="nav-logo" href="/">Profile<span>Host</span></a>
  <div class="nav-links">
    <a href="/">Browse</a>
    <a href="/create" class="btn-primary">+ Create Profile</a>
  </div>
</nav>
""";
    }

    private static String footer() {
        return """
<footer>
  <span class="footer-logo">ProfileHost</span>
  Built with <strong>Java 17</strong> · Maven · Jakarta Servlet API &nbsp;·&nbsp;
  <a href="/create">Create your profile →</a>
</footer>
</body></html>
""";
    }

    // ──────────────────────────────────────────────────────────────────────────
    // HOME PAGE
    // ──────────────────────────────────────────────────────────────────────────
    public static String buildHomePage(List<Profile> profiles) {
        StringBuilder sb = new StringBuilder();
        sb.append(head("Discover Profiles"));
        sb.append(nav());

        // Hero
        sb.append("""
<div class="hero">
  <div class="hero-eyebrow">● Live · %d profiles hosted</div>
  <h1>Where <em>People</em><br/>Build Their Story</h1>
  <p class="hero-sub">A beautiful home for your professional identity. Share your skills, projects, and personality with the world.</p>
  <div class="hero-cta fade-up-3">
    <a class="cta-btn cta-primary" href="/create">✦ Create Your Profile</a>
    <a class="cta-btn cta-secondary" href="#profiles">Browse Profiles ↓</a>
  </div>
</div>
""".formatted(profiles.size()));

        // Stats
        int totalViews = profiles.stream().mapToInt(Profile::getViews).sum();
        int totalSkills = profiles.stream().mapToInt(p -> p.getSkills().size()).sum();
        sb.append("""
<div class="stats-bar fade-up-4">
  <div class="stat"><span class="stat-n">%d</span><span class="stat-l">Profiles</span></div>
  <div class="stat"><span class="stat-n">%s</span><span class="stat-l">Total Views</span></div>
  <div class="stat"><span class="stat-n">%d</span><span class="stat-l">Skills Listed</span></div>
</div>
""".formatted(profiles.size(), totalViews > 1000 ? (totalViews/1000) + "k+" : String.valueOf(totalViews), totalSkills));

        // Profile grid
        sb.append("<div class=\"section\" id=\"profiles\">");
        sb.append("""
<div class="section-header">
  <h2 class="section-title">All <span>Profiles</span></h2>
</div>
<div class="profile-grid">
""");

        for (Profile p : profiles) {
            sb.append(buildProfileCard(p));
        }

        sb.append("</div></div>"); // end grid + section
        sb.append(footer());
        return sb.toString();
    }

    private static String buildProfileCard(Profile p) {
        String skillTags = p.getSkills().stream()
                .limit(3)
                .map(s -> "<span class='skill-tag'>" + s + "</span>")
                .reduce("", String::concat);
        if (p.getSkills().size() > 3) {
            skillTags += "<span class='skill-tag'>+" + (p.getSkills().size()-3) + "</span>";
        }

        return """
<a class="profile-card" href="/p/%s">
  <div class="card-cover">
    <div class="card-cover-inner" style="background: linear-gradient(135deg, %s 0%%, %s 100%%);"></div>
  </div>
  <div class="card-body">
    <div class="card-avatar">
      <img src="%s" alt="%s" loading="lazy"/>
    </div>
    <div class="card-name">%s</div>
    <div class="card-tagline">%s</div>
    <div class="card-meta">
      %s
      <span>👁 %s</span>
    </div>
    <div class="skills-row">%s</div>
  </div>
</a>
""".formatted(
                p.getUsername(),
                p.getCoverColor(), adjustColor(p.getCoverColor()),
                p.getAvatarUrl() != null ? p.getAvatarUrl() : "https://api.dicebear.com/7.x/bottts-neutral/svg?seed=" + p.getUsername(),
                p.getDisplayName(),
                p.getDisplayName(),
                p.getTagline() != null ? p.getTagline() : "",
                p.getLocation() != null ? "<span>📍 " + p.getLocation() + "</span>" : "",
                p.getViews() > 1000 ? (p.getViews()/1000) + "k" : String.valueOf(p.getViews()),
                skillTags
        );
    }

    // ──────────────────────────────────────────────────────────────────────────
    // PROFILE PAGE
    // ──────────────────────────────────────────────────────────────────────────
    public static String buildProfilePage(Profile p) {
        StringBuilder sb = new StringBuilder();
        sb.append(head(p.getDisplayName()));
        sb.append(nav());

        String avatarUrl = p.getAvatarUrl() != null ? p.getAvatarUrl()
                : "https://api.dicebear.com/7.x/bottts-neutral/svg?seed=" + p.getUsername();

        // Cover
        sb.append("""
<div class="profile-hero">
  <div class="profile-cover" style="background: linear-gradient(135deg, %s 0%%, %s 100%%);"></div>
  <div class="profile-cover-gradient"></div>
</div>
""".formatted(p.getCoverColor(), adjustColor(p.getCoverColor())));

        sb.append("<div class='profile-main'>");

        // Avatar + name
        sb.append("""
<div class="profile-avatar-wrap fade-up">
  <div class="profile-avatar">
    <img src="%s" alt="%s"/>
  </div>
</div>
<div class="fade-up-2">
  <div class="profile-name">%s</div>
  %s
  <div class="profile-meta-row">
    %s%s%s
    <span class="view-counter">👁 %s views</span>
  </div>
</div>
""".formatted(
                avatarUrl, p.getDisplayName(),
                p.getDisplayName(),
                p.getTagline() != null ? "<div class='profile-tagline'>" + p.getTagline() + "</div>" : "",
                p.getLocation() != null ? "<span>📍 " + p.getLocation() + "</span>" : "",
                p.getRole() != null ? "<span>💼 " + p.getRole() + (p.getCompany() != null ? " @ " + p.getCompany() : "") + "</span>" : "",
                p.getWebsite() != null ? "<span>🔗 <a href='" + p.getWebsite() + "' style='color:var(--gold);text-decoration:none' target='_blank'>" + p.getWebsite().replace("https://","") + "</a></span>" : "",
                p.getViews()
        ));

        sb.append("<div class='profile-grid2 fade-up-3'>");

        // LEFT column
        sb.append("<div>");

        // Bio
        if (p.getBio() != null && !p.getBio().isBlank()) {
            sb.append("""
<div class="card-block">
  <div class="block-title">About</div>
  <p class="bio-text">%s</p>
</div>
""".formatted(p.getBio()));
        }

        // Projects
        if (!p.getProjects().isEmpty()) {
            sb.append("<div class='card-block'><div class='block-title'>Projects</div>");
            for (Profile.Project proj : p.getProjects()) {
                String statusClass = switch(proj.getStatus()) {
                    case "wip" -> "status-wip";
                    case "archived" -> "status-archived";
                    default -> "status-live";
                };
                String statusLabel = switch(proj.getStatus()) {
                    case "wip" -> "● In Progress";
                    case "archived" -> "Archive";
                    default -> "● Live";
                };
                sb.append("""
<div class="project-card">
  <div style="display:flex;align-items:center;justify-content:space-between">
    <span class="project-title">%s</span>
    <span class="status-badge %s">%s</span>
  </div>
  <p class="project-desc">%s</p>
  <div class="project-tech">%s</div>
</div>
""".formatted(proj.getTitle(), statusClass, statusLabel, proj.getDescription(), proj.getTech()));
            }
            sb.append("</div>");
        }

        // Skills (full)
        if (!p.getSkills().isEmpty()) {
            sb.append("<div class='card-block'><div class='block-title'>Skills & Technologies</div><div class='skills-list'>");
            for (String skill : p.getSkills()) {
                sb.append("<span class='skill-pill'>" + skill + "</span>");
            }
            sb.append("</div></div>");
        }

        sb.append("</div>"); // end left column

        // RIGHT column
        sb.append("<div>");

        // Social Links
        if (!p.getSocialLinks().isEmpty()) {
            sb.append("<div class='card-block'><div class='block-title'>Connect</div>");
            for (Profile.SocialLink link : p.getSocialLinks()) {
                sb.append("""
<a class="social-link" href="%s" target="_blank" rel="noopener">
  <div class="social-icon">%s</div>
  <span>%s</span>
</a>
""".formatted(link.getUrl(), link.getPlatform().substring(0,2).toUpperCase(), link.getPlatform()));
            }
            sb.append("</div>");
        }

        // Profile info card
        sb.append("<div class='card-block'><div class='block-title'>Details</div>");
        if (p.getEmail() != null) sb.append("<div style='font-size:0.83rem;color:var(--text-dim);margin-bottom:0.5rem'>✉ " + p.getEmail() + "</div>");
        if (p.getCompany() != null) sb.append("<div style='font-size:0.83rem;color:var(--text-dim);margin-bottom:0.5rem'>🏢 " + p.getCompany() + "</div>");
        sb.append("<div style='font-size:0.75rem;color:var(--text-muted);margin-top:0.75rem;font-family:JetBrains Mono,monospace'>ID: " + p.getId() + "</div>");
        sb.append("</div>");

        // Share card
        sb.append("""
<div class="card-block" style="text-align:center">
  <div class="block-title" style="justify-content:center">Share Profile</div>
  <div style="font-family:'JetBrains Mono',monospace;font-size:0.75rem;
              background:var(--ink3);padding:0.6rem 1rem;border-radius:6px;
              color:var(--gold);margin-bottom:1rem;word-break:break-all">
    profilehost.io/p/%s
  </div>
  <button onclick="navigator.clipboard.writeText(window.location.href);this.textContent='✓ Copied!';setTimeout(()=>this.textContent='Copy Link',2000)"
          style="width:100%%;padding:0.6rem;background:var(--ink3);
                 border:1px solid var(--border2);color:var(--text);
                 border-radius:8px;cursor:pointer;font-family:'DM Sans',sans-serif;
                 font-size:0.85rem;transition:all 0.2s">
    Copy Link
  </button>
</div>
""".formatted(p.getUsername()));

        sb.append("</div>"); // end right
        sb.append("</div>"); // end profile-grid2
        sb.append("</div>"); // end profile-main
        sb.append(footer());
        return sb.toString();
    }

    // ──────────────────────────────────────────────────────────────────────────
    // CREATE PAGE
    // ──────────────────────────────────────────────────────────────────────────
    public static String buildCreatePage(String error) {
        StringBuilder sb = new StringBuilder();
        sb.append(head("Create Profile"));
        sb.append(nav());
        sb.append("<div class='create-wrap'>");
        sb.append("""
<div class="form-title fade-up">Create Your <em style="font-style:italic;
  background:linear-gradient(135deg,var(--gold),var(--gold2));
  -webkit-background-clip:text;-webkit-text-fill-color:transparent;
  background-clip:text">Profile</em></div>
<p class="form-sub fade-up-2">Your professional identity, beautifully presented.</p>
""");

        if (error != null && !error.isBlank()) {
            sb.append("<div class='error-banner'>⚠ " + error + "</div>");
        }

        sb.append("""
<form method="POST" action="/create" class="fade-up-3">
  <div class="form-section">
    <div class="form-section-label">Identity</div>
    <div class="form-group">
      <label for="username">Username *</label>
      <input type="text" id="username" name="username" placeholder="e.g. shreeni" required
             pattern="[a-zA-Z0-9_-]{3,30}" maxlength="30"/>
      <div class="tip">3–30 characters, letters, numbers, _ or -. This becomes your URL.</div>
    </div>
    <div class="form-row">
      <div class="form-group">
        <label for="displayName">Display Name</label>
        <input type="text" id="displayName" name="displayName" placeholder="Your full name" maxlength="60"/>
      </div>
      <div class="form-group">
        <label for="tagline">Tagline</label>
        <input type="text" id="tagline" name="tagline" placeholder="One-liner about you" maxlength="120"/>
      </div>
    </div>
    <div class="form-group">
      <label for="bio">Bio</label>
      <textarea id="bio" name="bio" placeholder="Tell your story..." maxlength="600"></textarea>
    </div>
  </div>

  <div class="form-section">
    <div class="form-section-label">Work</div>
    <div class="form-row">
      <div class="form-group">
        <label for="role">Role / Title</label>
        <input type="text" id="role" name="role" placeholder="e.g. DevOps Engineer" maxlength="80"/>
      </div>
      <div class="form-group">
        <label for="company">Company</label>
        <input type="text" id="company" name="company" placeholder="e.g. CorroHealth" maxlength="80"/>
      </div>
    </div>
    <div class="form-row">
      <div class="form-group">
        <label for="location">Location</label>
        <input type="text" id="location" name="location" placeholder="City, Country" maxlength="80"/>
      </div>
      <div class="form-group">
        <label for="website">Website</label>
        <input type="url" id="website" name="website" placeholder="https://yoursite.com"/>
      </div>
    </div>
    <div class="form-group">
      <label for="email">Email</label>
      <input type="email" id="email" name="email" placeholder="hello@example.com"/>
    </div>
  </div>

  <div class="form-section">
    <div class="form-section-label">Skills</div>
    <div class="form-group">
      <label for="skills">Skills (comma-separated)</label>
      <input type="text" id="skills" name="skills" placeholder="Java, Python, Docker, Kubernetes, AWS"/>
      <div class="tip">Separate each skill with a comma.</div>
    </div>
  </div>

  <div class="form-section">
    <div class="form-section-label">Appearance</div>
    <div class="form-row">
      <div class="form-group">
        <label for="theme">Theme</label>
        <select id="theme" name="theme">
          <option value="dark">Dark</option>
          <option value="light">Light</option>
          <option value="neon">Neon</option>
          <option value="minimal">Minimal</option>
        </select>
      </div>
      <div class="form-group">
        <label for="coverColor">Cover Color</label>
        <div class="color-row">
          <input type="color" id="coverColor" name="coverColor" value="#6366f1"/>
          <input type="text" id="coverColorHex" placeholder="#6366f1" maxlength="7"
                 style="flex:1" onchange="document.getElementById('coverColor').value=this.value"/>
        </div>
      </div>
    </div>
  </div>

  <button type="submit" class="submit-btn">✦ Create Profile →</button>
</form>
<script>
document.getElementById('coverColor').addEventListener('input',function(){
  document.getElementById('coverColorHex').value=this.value;
});
</script>
""");

        sb.append("</div>"); // create-wrap
        sb.append(footer());
        return sb.toString();
    }

    // ──────────────────────────────────────────────────────────────────────────
    // 404 PAGE
    // ──────────────────────────────────────────────────────────────────────────
    public static String build404Page(String username) {
        return head("Not Found") + nav() + """
<div style="text-align:center;padding:8rem 2rem">
  <div style="font-family:'Playfair Display',serif;font-size:6rem;
              color:var(--gold);opacity:0.3;font-weight:900;line-height:1">404</div>
  <div style="font-family:'Playfair Display',serif;font-size:2rem;
              color:var(--text);margin-top:1rem">Profile not found</div>
  <p style="color:var(--text-muted);margin-top:0.75rem">
    No profile exists for <code style="font-family:'JetBrains Mono',monospace;
    color:var(--gold)">%s</code>
  </p>
  <a href="/create?username=%s" style="display:inline-block;margin-top:2rem;
     padding:0.75rem 2rem;background:linear-gradient(135deg,var(--gold),var(--gold2));
     color:var(--ink);font-weight:700;text-decoration:none;border-radius:10px">
    Claim this username →
  </a>
</div>
""".formatted(username, username) + footer();
    }

    // ──────────────────────────────────────────────────────────────────────────
    // UTILITY
    // ──────────────────────────────────────────────────────────────────────────
    private static String adjustColor(String hex) {
        // Darken the second gradient stop slightly
        try {
            if (!hex.startsWith("#") || hex.length() < 7) return hex;
            int r = Integer.parseInt(hex.substring(1,3), 16);
            int g = Integer.parseInt(hex.substring(3,5), 16);
            int b = Integer.parseInt(hex.substring(5,7), 16);
            r = Math.max(0, r - 40);
            g = Math.max(0, g - 40);
            b = Math.max(0, b + 20);
            return String.format("#%02x%02x%02x", r, g, b);
        } catch (Exception e) {
            return hex;
        }
    }
}
