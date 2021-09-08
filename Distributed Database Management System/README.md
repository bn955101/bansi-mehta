# Distributed Database Management System

This	project was created with an aim to lean about concepts in modelling notation and how they would be used. The objective also covers learning and implementation of most common design for core database system components including	the	query	optimizer,	query	executor,	storage	manager,	access	methods,	and	transaction	processor.


* Date Created: January, 2021
* Technology: **Python**
 
## Authors

* [Bansi Mehta(B00875640)](bn955101@dal.ca) - (Developer)

## Getting Started

### Prerequisites

To have a local copy of this project up and running on your local machine, you will first need to install:

* Python 3+
* IDE of choice (Pycharm was used here)

### Installing

##### Python
Python: [download and install](https://www.python.org/downloads/)

##### Installling Pip
To install Pip [Click Here](https://packaging.python.org/tutorials/installing-packages/)

##### Upgrading Pip

Run the following command to upgrade pip

```
python -m pip install --upgrade pip
```

Check python is installed by running the following command:

```
python --version
```

### Running on Local Machine

Run the following command to start the project locally:

```
python main.py
```

## Additional Libraries

* sys
* os
* json
* datetime
* re

## Sources Used

* [Installing Python Packages](https://packaging.python.org/tutorials/installing-packages/)
* [Python Programing](https://www.tutorialspoint.com/python/index.htm)
* [Python regular expressions](https://www.tutorialspoint.com/python/python_reg_expressions.htm)
* [Python file IO](https://www.tutorialspoint.com/python/python_files_io.htm)

## Main Files
* [main.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/main.py): Entry point of the program. Prompts the user to login or register.
* [login_console.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/login_console.py): Gets login information from user and authenticates the user.
* [registration_console.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/registration_console.py): Allows a new user to register in the system.
* [query_console.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/query_console.py): After authentication, allows the user to input queries.
* [query_parser.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/query_parser.py): Responsible for validating and parsing the queries.
* [execution_engine.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/execution_engine.py): Responsible for execution of the queries and displaying output.

## Other supporting files
* [column_data_type.py](https://github.com/bn955101/bansi-mehta/blob/main/Distributed%20Database%20Management%20System/column_data_type.py)
## Acknowledgments

* Saurabh Dey
* Juhil Zaladiya
* Akash Madan
