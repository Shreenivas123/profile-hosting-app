# ProfileHost — Java 17 Maven WAR

A beautifully designed **Profile Hosting Application** built with:

- **Java 17** (LTS)
- **Maven** (WAR packaging)
- **Jakarta Servlet API 6.0**
- **Jackson** (JSON serialization)
- No framework bloat — pure Servlet + Java

---

## Project Structure

```
profile-hosting-app/
├── pom.xml
└── src/main/
    ├── java/com/profilehost/
    │   ├── model/
    │   │   ├── Profile.java           ← Domain model (POJO)
    │   │   └── ProfileStore.java      ← In-memory store (ConcurrentHashMap)
    │   └── servlet/
    │       ├── HomeServlet.java        ← GET / → profile listing
    │       ├── ProfileViewServlet.java ← GET /p/{username}
    │       ├── CreateProfileServlet.java ← GET+POST /create
    │       ├── ProfileApiServlet.java  ← GET /api/profiles
    │       ├── CharacterEncodingFilter.java
    │       └── HtmlTemplates.java      ← All HTML (no JSP needed)
    └── webapp/WEB-INF/
        └── web.xml
```

---

## Prerequisites

- JDK 17+
- Maven 3.8+
- Tomcat 10+ (or any Jakarta EE 10 servlet container)

---

## Build

```bash
# Package as WAR
mvn clean package

# Output: target/profile-hosting-app.war
```

---

## Deploy

### Option A: Tomcat

```bash
cp target/profile-hosting-app.war $CATALINA_HOME/webapps/ROOT.war
$CATALINA_HOME/bin/startup.sh
# Open http://localhost:8080
```

### Option B: Maven Embedded Tomcat (dev only)

```bash
mvn tomcat7:run
# Open http://localhost:8080
```

---

## Pages & Routes

| Route | Description |
|-------|-------------|
| `GET /` | Home — browse all profiles |
| `GET /p/{username}` | View a profile |
| `GET /create` | Create profile form |
| `POST /create` | Submit new profile |
| `GET /api/profiles` | JSON — all profiles |
| `GET /api/profiles/{id}` | JSON — single profile |

---

## Design

- **Typography**: Playfair Display (serif headings) + DM Sans (body) + JetBrains Mono (code)
- **Theme**: Editorial ink-black luxury with champagne gold accents
- **Seeded profiles**: shreeni, akira, maya — ready to browse on first launch

---

## Production Readiness

For production, replace `ProfileStore` (in-memory) with:

- **JPA/Hibernate** + PostgreSQL or MySQL
- **Connection pooling** via HikariCP
- Add **authentication** (Jakarta Security / Spring Security)
- Add **file upload** servlet for real avatar photos

---

## WAR Compatibility

Tested with Tomcat 10.1+ (Jakarta EE 10). For older Tomcat 9 (Java EE 8),
change `jakarta.*` imports to `javax.*` and use servlet-api 4.0.x.
