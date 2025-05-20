# HRMS Portal

A custom-built Human Resource Management System (HRMS) portal developed using **Liferay DXP**. This portal supports employee onboarding, profile management, and admin-level control using Service Builder, MVC Portlet architecture, and a custom theme.

---

## 🧩 Features

- Employee CRUD (Create, Read, Update, Delete) operations.
- Role-based access control (Admin, HR Manager, Team Lead, Employee).
- Responsive and clean UI with a custom Liferay theme.
- Profile fields including PAN, Aadhaar, Passport, and image/document uploads.
- Navigation menu with header and footer customization.
- Modular codebase following Liferay best practices.

---

## 📦 Modules

### 1. `employee-service`
- Service Builder module.
- Contains the `Employee` entity and local/remote services.

### 2. `employee-web`
- MVC Portlet for managing employee records.
- JSP-based UI with backend logic calling `employee-service`.

### 3. `hrms-theme`
- Custom site theme using `_custom.scss` and Freemarker templates.
- Includes header, footer, and navigation styling.

---

## 🚀 Getting Started

### Prerequisites
- Liferay DXP 7.4+
- Java 11+
- Maven or Gradle
- Blade CLI (for development)
- MySQL or supported DB

### Setup Instructions

```bash
# Clone the repository
git clone https://github.com/yourusername/hrms-liferay-portal.git
cd hrms-liferay-portal

# Deploy modules
cd employee-service && ./gradlew deploy
cd ../employee-web && ./gradlew deploy
cd ../hrms-theme && ./gradlew deploy
