# Concurrent Money Transfer System

A thread-safe Java application for simulating concurrent money transfers between multiple accounts.
Includes a suite of unit tests to validate concurrency.

## ? Project Structure

src/
??? model/
? ??? Account.java
??? service/
? ??? AccountManager.java
???  test/
  ??? AccountManagerTest.java


## ? Prerequisites

- Java 11 or higher
- Maven (recommended) or your preferred build system
- Git (optional)
- IDE (optional but helpful): IntelliJ IDEA, VS Code, Eclipse

## ? Setup Instructions

### 1. Clone the Repository

mkdir -p out
javac -d out -cp "libs/*" src/model/Account.java src/service/AccountManager.java src/server/MoneyTransferHttpServer.java test/service/AccountManagerTest.java

### 2. Compile the Code
 javac -d out src/model/Account.java src/service/AccountManager.java src/server/MoneyTransferHttpServer.java src/test/AccountManagerTest.java

### 3. Run test
```bash
  ja -cp out test.AccountManagerTest
```

### 4. Run server

```bash
  java -cp src server.MoneyTransferHttpServer 
```
   
   ## Endpoints:
     curl http://localhost:8000/balances
     curl -X POST http://localhost:8000/transfer -d "from=Mark&to=Adam&amount=20"
     curl http://localhost:8000/balances

```bash
git clone <https://github.com/gehad-hamdy/moneyTransferSystem.git>
cd <moneyTransferSystem>   
```

