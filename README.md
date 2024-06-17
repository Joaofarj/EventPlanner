João Pedro Farjoun Silva / 13731319

Felipe Destaole / 13686768

# Event Planner

Event Planner is a desktop application built using Java Swing that helps users organize and manage events such as meetings, birthdays, and other appointments. It includes features like calendar views and event reminders.


## Project Requirements

- **Java Development Kit (JDK)**
  - Version: 11 or higher

- **Integrated Development Environment (IDE)**
  - IntelliJ IDEA, Eclipse, NetBeans, or any other Java-compatible IDE

- **Necessary Libraries**
  - Standard Java libraries included in the JDK required

- **Operating System**
  - Compatible with any operating system that supports Java (Windows, macOS, Linux)
    

## Features

- **User Interface**:
  - Java Swing is used to build the main window, event dialogs, and calendar views.
  - Navigation pane with calendar dates and a detailed view area where specific events for a selected date are displayed.

- **Event Management**:
  - Add, edit, delete, and view events with details such as title, date, time, location, and description.
  - Search functionality to find events by keywords.

- **Calendar Display**:
  - Monthly calendar view where users can click on a date to see events scheduled for that day.
  - Highlight dates with scheduled events.

- **Reminders and Notifications**:
  - Reminders alert users of upcoming events.

- **Data Persistence**:
  - Event data is saved and loaded using file I/O.
  - Exception handling for potential input/output errors.


## Tests

The tests conducted on the code were performed using the 'events.dat' file available in the 'Trabalho' folder. This file contains sample event data used to validate the functionality of the application. During the testing process, various operations such as adding, editing, and deleting events were executed to ensure the application's reliability and correctness. The results indicated that all features operated as expected, and no issues or errors were encountered. This successful testing demonstrates the robustness and stability of the code.


## Step-by-Step Build Instructions

### 1. Set Up the Development Environment

1. **Download and Install JDK:**
   - Go to the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
   - Choose the appropriate version for your operating system and download the installer.
   - Follow the installation instructions provided on the download page.
   - Verify the installation by running `java -version` and `javac -version` in your terminal or command prompt.

2. **Download and Install an IDE:**
   - Follow the instructions on the official website of your chosen IDE:
     - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
     - [Eclipse](https://www.eclipse.org/downloads/)
     - [NetBeans](https://netbeans.apache.org/download/index.html)

### 2. Clone or Download the Project

- Clone the repository using Git:
  ```sh
  git clone https://github.com/your-repository/eventPlanner.git

- Or download the project ZIP file and extract it.

### 3. Import the Project into the IDE

#### IntelliJ IDEA:
- Open IntelliJ IDEA and select "Open or Import" from the welcome screen.
- Navigate to the directory where the project is located and open it.
- IntelliJ IDEA will automatically detect the project structure and set it up.

#### Eclipse:
- Open Eclipse and go to `File > Import`.
- Select `Existing Projects into Workspace` and click `Next`.
- Browse to the project directory and finish the import.

#### NetBeans:
- Open NetBeans and go to `File > Open Project`.
- Navigate to the project directory and open it.

### 5. Compile the Project

In your IDE, locate the build or compile option.
- **IntelliJ IDEA:** `Build > Build Project`
- **Eclipse:** `Project > Build All`
- **NetBeans:** `Build > Build Main Project`

### 6. Run the Project

After successfully building the project, you can run it:
- **IntelliJ IDEA:** `Run > Run 'MainWindow'`
- **Eclipse:** Right-click on the `MainWindow` class and select `Run As > Java Application`.
- **NetBeans:** Right-click on the project and select `Run`.

### 7. Verify Application Functionality

Upon running the project, a main window should appear, allowing you to add, edit, and manage events.
Conduct various operations to ensure all functionalities are working correctly.

### 8. Testing the Application

- The tests for the application were conducted using the `events.dat` file.
- Place the `events.dat` file in the appropriate directory required by the code.
- Run the application and verify that it correctly reads from and writes to this file.
- The tests confirmed that the application works as intended, with no issues encountered.



