📌 Project Overview
This repository contains the evaluated project for the Programming in Java course. It features a standalone, terminal-executable Smart Logistics Management System designed to record, categorize, compute shipment delivery fees, and persist package records locally. The project demonstrates core Object-Oriented Programming (OOP) concepts including Encapsulation, Abstraction, Inheritance, and Method Overriding, while maintaining zero external dependencies.

📂 Repository Structure
src/: Contains the Java source code (SmartLogisticsSystem.java).
bin/: Directory where compiled bytecodes are generated.
README.md: Project documentation and execution guide (this file).

🛠 System Features & Functionality
This application was developed using JDK 17+ to demonstrate core Java competencies:
- Object-Oriented Hierarchy: Uses an abstract Shipment base class with specialized ExpressShipment and CargoShipment subclasses.
- Dynamic Cost Calculation: Calculates delivery fees on the fly based on weight, priority handling, and refrigeration requirements.
- Local Data Persistence: Employs standard Java I/O streams (BufferedReader, PrintWriter) to read and write records to shipments_data.txt.
- Interactive CLI: Provides a robust terminal menu with error handling for user inputs.

🚀 How to Run
Clone the repository:
git clone (https://github.com/Fireflie07/java_project-24bac10013)
cd java-project-24bac10013

Compile Java Files:
javac -d bin src/SmartLogisticsSystem.java

Execute Application:
java -cp bin SmartLogisticsSystem

⚖️ License
This project is open-source and released under the MIT License.

👤 Author
Name: Elizabeth Maria George
Registration Number: 24BAC10013
Course: Programming in Java
