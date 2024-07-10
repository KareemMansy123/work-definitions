Dictionary App
Overview
The Dictionary App is a modern Android application that allows users to search for word definitions using the Dictionary API. The application is built using the MVVM architecture and Jetpack Compose for the UI. It includes features like fetching word definitions from the API, caching previously fetched definitions in a local database, and providing offline functionality.

Features
Search Word Definitions: Fetch word definitions from the Dictionary API.
Offline Support: Cache previously fetched word definitions and use them when offline.
Modern UI: Built using Jetpack Compose for a responsive and modern user interface.
MVVM Architecture: Clean separation of concerns with the Model-View-ViewModel architecture.
Error Handling: Graceful handling of errors such as no internet connection or word not found.
Loading Indicator: Show loading state while fetching data from the API.
Screenshots

Architecture
The application follows the MVVM architecture pattern, ensuring a clean separation of concerns and making the codebase more maintainable and testable.
com.example.worddefinitions
├── core
│   └── data
│       ├── local
│       │   ├── maper
│       │   │   └── ModelMappers.kt
│       │   ├── model
│       │   │   └── WordDefinitionEntity.kt
│       │   ├── Converters.kt
│       │   ├── WordDao.kt
│       │   └── WordDatabase.kt
│       ├── remote
│       │   ├── model
│       │   │   └── WordModel.kt
│       │   ├── DictionaryApiService.kt
│       │   ├── RetrofitInstance.kt
│       └── repository
│           └── WordRepository.kt
├── ui
│   ├── components
│   │   ├── ErrorMessage.kt
│   │   ├── LoadingIndicator.kt
│   │   ├── SearchInput.kt
│   │   ├── TopBar.kt
│   │   └── WordDefinitionCard.kt
│   ├── screen
│   │   └── MainScreen.kt
│   ├── theme
│   └── DictionaryApp.kt
└── vm
    ├── factory
    │   └── WordViewModelFactory.kt
    └── WordViewModel.kt

Usage
Search for a word: Enter a word in the search bar and press the "Search" button. The app will fetch the definition from the API and display it.
Offline functionality: The app caches previously searched word definitions. If you search for a word while offline, the app will display the cached definition if available.
Libraries and Tools
Jetpack Compose: Modern toolkit for building native Android UI.
Retrofit: Type-safe HTTP client for Android and Java.
Room: Persistence library provides an abstraction layer over SQLite to allow fluent database access.
Kotlin Coroutines: Concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
Hilt: Dependency injection library for Android.
Contributing
Contributions are welcome! Please fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
If you have any questions, feel free to reach out:

Kareem Mansy
kareemmansy123@gmail.com
