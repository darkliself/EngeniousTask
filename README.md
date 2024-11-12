# Engenius Task

This project is a simple Android application that pulls a list of users from the GitHub API and displays their names and pictures. It implements offline functionality, local data storage, search capabilities, and follows the MVVM architecture pattern for better separation of concerns and scalability.

### Libraries Used

- **Jetpack Compose**: For building the UI.
- **Room Database**: For local data storage.
- **Coil**: For image loading.
- **Retrofit**: For network requests.
- **Paging 3**: For efficient data loading and pagination.
- **Hilt**: For dependency injection.

## Features

- **User List Display**: Shows a list of users with their names and avatars.
- **Search Functionality**: Users can search for a specific user by nickname.
- **Local Storage**: User data is stored locally using Room Database.
- **Offline Support**: 
  - If the app is opened without an internet connection and no users are stored, it displays the message **"Search will not work"**.
  - If users are stored locally, the app shows the cached data and search functionality still works.
- **Network Restoration**: Once the network is restored, the app fetches fresh data from the GitHub API and updates the local database and UI.

### Installation

Clone the repository to your local machine:

```bash
git clone https://github.com/darkliself/EngeniousTask.git
