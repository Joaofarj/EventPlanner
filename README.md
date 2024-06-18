João Pedro Farjoun Silva / 13731319

Felipe Destaole / 13686768

# Event Planner

**Event Planner** is a Java Swing desktop application designed to help users organize and manage events such as meetings, birthdays, and appointments. It features calendar views and event reminders.

### Project Requirements

- **Java Development Kit (JDK)**
  - Version: 11 or higher

- **Integrated Development Environment (IDE) (Optional)**
  - IntelliJ IDEA, Eclipse, NetBeans, or any other Java-compatible IDE

- **Required Libraries**
  - Standard Java libraries included in the JDK

- **Operating System**
  - Compatible with any operating system that supports Java (Windows, macOS, Linux)

### Features

**User Interface:**
- Utilizes Java Swing for building the main window, event dialogs, and calendar views
- Navigation pane with calendar dates and a detailed view area to display specific events for selected dates

**Event Management:**
- Add, edit, delete, and view events with details like title, date, time, location, and description
- Search functionality to find events using keywords

**Calendar Display:**
- Monthly calendar view where users can click on a date to see scheduled events for that day
- Highlights dates with scheduled events

**Reminders and Notifications:**
- Reminders alert users about upcoming events

**Data Persistence:**
- Event data is saved and loaded using file I/O
- Exception handling for potential input/output errors

### Testing

- Testing was conducted using the 'events.dat' file available in the project folder. This file contains sample event data used to validate the application's functionality. Various operations such as adding, editing, and deleting events were executed during testing to ensure reliability and correctness. The results confirmed that all features operated as expected without encountering issues or errors.


### Step-by-Step Build Instructions

1. **Set Up the Development Environment**
   - **Download and Install JDK:**
     - Visit the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
     - Choose the appropriate version for your operating system and download the installer
     - Follow the installation instructions provided on the download page
     - Verify the installation by running `java -version` and `javac -version` in your terminal or command prompt

   - **Download and Install an IDE (Optional):**
     - Follow the instructions on the official website of your chosen IDE: [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/), [NetBeans](https://netbeans.apache.org/)

2. **Clone or Download the Project**
   - Clone the repository using Git:
     ```sh
     git clone https://github.com/your-repository/eventPlanner.git
     ```
     Or download the project ZIP file and extract it.

3. **Import the Project into the IDE (Optional)**
   - **IntelliJ IDEA:**
     - Open IntelliJ IDEA and select "Open or Import" from the welcome screen.
     - Navigate to the directory where the project is located and open it.
     - IntelliJ IDEA will automatically detect the project structure and set it up.

   - **Eclipse:**
     - Open Eclipse and go to `File > Import`.
     - Select `Existing Projects into Workspace` and click `Next`.
     - Browse to the project directory and finish the import.

   - **NetBeans:**
     - Open NetBeans and go to `File > Open Project`.
     - Navigate to the project directory and open it.

4. **Compile the Project**

   - **Using IDE (Optional):**
     - IntelliJ IDEA: `Build > Build Project`
     - Eclipse: `Project > Build All`
     - NetBeans: `Build > Build Main Project`

   - **Using Terminal:**
     ```sh
     javac MainWindow.java
     ```

5. **Run the Project**

   - **Using IDE (Optional):**
     - IntelliJ IDEA: `Run > Run 'MainWindow'`
     - Eclipse: Right-click on the `MainWindow` class and select `Run As > Java Application`.
     - NetBeans: Right-click on the project and select `Run`.

   - **Using Terminal:**
     ```sh
     java MainWindow
     ```

6. **Verify Application Functionality**
   - Upon running the project, a main window should appear, allowing you to add, edit, and manage events.
   - Conduct various operations to ensure all functionalities are working correctly.

7. **Test the Application**

   - Testing for the application was performed using the `events.dat` file.
   - Place the `events.dat` file in the appropriate directory as required by the code.
   - **Run Tests Using Terminal:**
     ```sh
     java MainWindow
     ```
   - Verify that the application correctly reads from and writes to this file.
   - Tests confirmed that the application functions as intended, with no issues encountered.

   - Testing for the application was performed using the events.dat file
   - Place the events.dat file in the appropriate directory as required by the code
   - Run the application and verify that it correctly reads from and writes to this file
   - Tests confirmed that the application functions as intended, with no issues encountered
