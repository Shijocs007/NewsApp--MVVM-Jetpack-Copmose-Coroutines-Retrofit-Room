# News Application 📰

A modern news application built using **Clean Architecture** to ensure scalability and maintainability. This project follows the **MVVM** design pattern and incorporates the latest Android development best practices.

## Tech Stack
- **Architecture**: Clean Architecture, MVVM
- **Networking**: Retrofit
- **Local Storage**: Room Database, Preferences DataStore
- **Asynchronous Programming**: Coroutines, Flow
- **Language**: Kotlin
- **Dependency Injection**: Dagger-Hilt

## Features
1. **Onboarding Screen**
    - An interactive onboarding screen slides through essential information about the app for first-time users.

2. **Headline Screen**
    - Displays the top headlines of the day for the user to stay updated.

3. **Country Selection**
    - Users can select a country from the top bar, and the headlines update dynamically based on the selected country.

4. **Instant Search with Pagination**
    - A powerful search feature fetches news articles from the API in real time and supports pagination for smooth scrolling through results.

5. **Bookmarks**
    - Users can save news articles to bookmarks for later viewing. Bookmarked articles are accessible offline with the help of the Room Database.  
