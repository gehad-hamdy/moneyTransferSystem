# Concurrent Money Transfer System

A thread-safe Java application for simulating concurrent money transfers between multiple accounts.
Includes a suite of unit tests to validate concurrency.

## ? Project Structure

    src/
    ??? model/
    ? ??? Account.java
    ??? service/
    ? ??? AccountManager.java
    ??? test/
      ??? AccountManagerTest.java


## ? Prerequisites

- Java 11 or higher
- Git (optional)
- IDE (optional but helpful): IntelliJ IDEA, VS Code, Eclipse

## ? Setup Instructions

### 1. Clone the Repository

```bash
    git clone https://github.com/gehad-hamdy/moneyTransferSystem.git
    cd moneyTransferSystem   
```
 #### If you don't have Git installed, you can download the ZIP file from the repository and extract it.

### 2. Compile the Code
```bash
  javac -d out src/model/Account.java src/service/AccountManager.java src/server/MoneyTransferHttpServer.java src/test/AccountManagerTest.java
```

### 3. Run test
```bash
  ja -cp out test.AccountManagerTest
```

### 4. Run server

```bash
  java -cp src server.MoneyTransferHttpServer 
```
   
### 5. Endpoints:
```bash
  curl http://localhost:8000/balances
  curl -X POST http://localhost:8000/transfer -d "from=Mark&to=Adam&amount=20"
  curl http://localhost:8000/balances
```

