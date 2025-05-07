# Spring MVC Hibernate Employee Management System

A web application for managing employees and departments using Spring MVC and Hibernate ORM.

## Description

This application provides a simple interface for managing employees and departments within an organization. It allows users to:

- Create, read, update, and delete employees
- Create, read, update, and delete departments
- Assign employees to departments

The application demonstrates the use of Spring MVC for the web layer, Hibernate for ORM, and PostgreSQL for data storage.

## Technologies Used

- **Java 21**
- **Spring MVC 6.2.6** - Web framework
- **Hibernate ORM 6.6.13** - Object-relational mapping
- **Hibernate Validator 8.0.2** - Form validation
- **PostgreSQL 42.7.5** - Database
- **JSP & JSTL—**View templates
- **Bootstrap—**UI styling
- **Maven—**Build tool

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── org/chintanpatel/springmvchibernate/
│   │       ├── config/           # Application configuration
│   │       ├── controller/       # MVC controllers
│   │       ├── model/            # Entity classes
│   │       ├── repository/       # Data access layer
│   │       ├── service/          # Business logic layer
│   │       └── validator/        # Custom validators
│   ├── resources/
│   │   └── db.properties         # Database configuration
│   └── webapp/
│       ├── WEB-INF/
│       │   └── views/            # JSP view templates
│       └── resources/
│           ├── css/              # CSS stylesheets
│           └── js/               # JavaScript files
└── test/
    └── java/                     # Test classes
```

## Setup and Installation

### Prerequisites

- Java Development Kit (JDK) 21
- Maven
- PostgreSQL database

### Database Setup

1. Install PostgreSQL if not already installed
2. Create a database named `postgres` (or modify `db.properties` to use a different database)
3. Set username to `postgres` and password to `root` (or modify `db.properties` with your credentials)

### Application Setup

1. Clone the repository
   ```
   git clone https://github.com/yourusername/SpringMVCHibernate.git
   cd SpringMVCHibernate
   ```

2. Build the application
   ```
   mvn clean install
   ```

3. Deploy the WAR file to a servlet container like Tomcat or run using Maven
   ```
   mvn jetty:run
   ```

4. Access the application at `http://localhost:8080/SpringMVCHibernate/`

## Usage

### Managing Departments

1. Click on the "Department" link from the home page
2. Use the form to add a new department
3. View the list of departments below the form
4. Click "Edit" to modify a department or "Delete" to remove it

### Managing Employees

1. Click on the "Employee" link from the home page
2. Fill out the form to add a new employee
   - All fields are required
   - Select a department from the dropdown
   - Choose programming languages
3. View the list of employees below the form
4. Click "Edit" to modify an employee or "Delete" to remove it

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## License

This project is licensed under the MIT License—see the LICENSE file for details.

## Author

Chintan Patel