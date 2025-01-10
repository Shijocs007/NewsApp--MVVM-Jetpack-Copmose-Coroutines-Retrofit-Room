# News Application 📰

A modern news application built using **Clean Architecture** to ensure scalability and maintainability. This project follows the **MVVM** design pattern and incorporates the latest Android development best practices.

## How to Run the Project
1. Clone the repository and open it in **Android Studio**:
   ```bash
   git https://github.com/Shijocs007/NewsApp--MVVM-Jetpack-Copmose-Coroutines-Retrofit-Room.git
   
2. Go to https://newsapi.org/ and create an API key.
3. In the app/build.gradle file, replace the API_KEY in the buildConfigField with your own API key:
   ```bash
   buildConfigField("String", "API_KEY", "\"your_api_key_here\"")
4. Sync the project and run the app on your device or emulator.

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
