[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/R_7cjhEg)
# A2-PersistUI

## Pair Information
- **Name:** Shlok Patel
- **ID:** 2021A7PS2441G
- **Email:** f20212441@goa.bits-pilani.ac.in <br /> <br/>
- **Name:** Suraz Kumar
- **ID:** 2021B2A71602G
- **Email:** f20211602@goa.bits-pilani.ac.in

## Project Overview

This app simulates a Wallet Activity for earning and losing coins based on dice rolls, implementing UI state persistence through Android's `ViewModel`. The app supports both portrait and landscape orientations and includes accessibility features for users interacting with the app via TalkBack.

### Features:
- A **toast** is shown with "Congratulations!" when the user earns coins by rolling a six.
- Displays the **total number of dice rolls** and the **number of sixes rolled**.
- Implements a rule system where:
    - Rolling a six gives 5 coins.
    - Rolling two sixes in a row gives 10 coins.
    - Rolling any other number twice in a row results in a loss of 5 coins.
- Displays **statistics** of the current and previous dice rolls.
- The **UI state persists** over configuration changes using `ViewModel`.

## Tasks Completed

### 1. WalletActivity Implementation:
- Created the WalletActivity using the `ViewModel` to persist the state, ensuring the app can handle configuration changes, like screen rotation.
- An alternate layout for landscape mode was added, ensuring that all elements are visible and interactable in both orientations.

### 2. Toast Implementation:
- Implemented a toast that shows the message "Congratulations!" whenever the user earns coins.
- The toast is managed within the activity to avoid UI logic in the `ViewModel`.

### 3. UI and Win Rate:
- Added UI elements that display the **total number of rolls** and how many times a six was rolled.
- The values update in real-time and persist over configuration changes using the `ViewModel`.

### 4. Coin Calculation Logic:
- Implemented the rule-based coin calculation:
    - Rolling a six awards 5 coins.
    - Rolling two sixes in a row awards 10 coins.
    - Rolling any other number twice in a row deducts 5 coins.
- The statistics, including the value of the previous roll, are displayed on the screen.

### Testing

- **JUnit Tests**: We implemented at least five JUnit tests to ensure the logic in the `ViewModel` works as expected. These tests cover scenarios like coin earning and loss based on dice rolls.
- **Instrumented Tests**: Five additional instrumented tests were added to verify the UI functionality, ensuring the toast appears correctly and the state is persisted across configuration changes.
- **Test-Driven Development**: We followed a partial TDD approach for the `ViewModel`. We tried writing some intuitive edge cases before fully implementing the code and hence ensure the robustness of the app.
- **Mockito**: Mockito was used to mock certain objects in the tests, especially when testing the ViewModel in isolation from the activity.
- **Monkey Tool:** The monkey tool was used to simulate random user interactions, which helped identify and address potential crashes.

### Accessibility

- **TalkBack**: When using the app with TalkBack, the user can smoothly navigate the dice rolls and understand when coins are earned or lost based on the auditory feedback.
- If the dice roll button were an `ImageButton`, I would ensure it has a content description, so it remains accessible via TalkBack.
- **Accessibility Scanner**: Initially, the Accessibility Scanner flagged some issues regarding small touch targets. I resolved these by increasing button sizes and ensuring adequate spacing between interactive elements.

## Stats
- **Hours Spent:** Approximately 10 hours
- **Pair Programming Usage Rating:** 4 out of 5
- **Assignment Difficulty Rating:** 7 out of 10

