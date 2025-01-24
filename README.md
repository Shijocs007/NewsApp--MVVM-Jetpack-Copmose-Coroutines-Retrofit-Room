# News Application 📰

A modern news application built using **Clean Architecture** to ensure scalability and maintainability. This project follows the **MVVM** design pattern and incorporates the latest Android development best practices.

## How to Run the Project
1. Clone the repository and open it in **Android Studio**:
   ```bash
   git https://github.com/Shijocs007/NewsApp--MVVM-Jetpack-Copmose-Coroutines-Retrofit-Room.git
   
2. Go to https://newsapi.org/ and create an API key.
3. Set up NDK for Secure API Key Storage.
   - Go to File > Settings (Windows/Linux) or Android Studio > Preferences (Mac).
   - In the SDK Manager window, select the SDK Tools tab.
   - Check CMake and NDK (Side by side) and click Apply to install them.
   
4. Replace the API Key
   - In the `app/cpp/` directory, open the `.cpp` file (e.g., `native-lib.cpp`).
   - Replace the placeholder `"YOUR_API_KEY_HERE"` with your actual API key.

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

## Screenshots 📱
![News App](https://firebasestorage.googleapis.com/v0/b/cybrillatest-ad60b.appspot.com/o/news-app-screenshots.png?alt=media&token=9d51d674-3aee-4503-8ae6-ba2b9e991eb3)
