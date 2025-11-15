***APP Overview***:
This app is built with Kotlin and Jetpack Compose that displays a list of users fetched from the JSONPlaceholder API. The app uses Retrofit to load the user data from the network and Room to cache the data locally
the device. The UI always reads from the Room database so users can still see the last fetched list even when there is no internet connection. A built-in search bar lets users filter the list by name or email using
a local Room query (no extra network calls).

***Offline first***
![Screenshot](<img width="426" height="959" alt="OfflineFirst" src="https://github.com/user-attachments/assets/3748bcc7-40bf-43e0-b10d-566053270e3e" />)

***Online***
![Screenshot](<img width="434" height="949" alt="Online" src="https://github.com/user-attachments/assets/96c9033c-35cb-46da-aa33-80bb81e20b7e" />)

***Offline after Online***
![Screenshot](<img width="420" height="886" alt="OfflineafterOnline" src="https://github.com/user-attachments/assets/80a1554e-b145-4c71-a8f2-5d7012bddf46" />)


***Core Functionality***
The app I created fetches users from `https://jsonplaceholder.typicode.com/users` with Retrofit, maps them into Room entities, and stores them in a local "users" table so the UI always reads its data from the database rather than directly from the network.
On startup the UI immediately displays users from Room via a Flow, then a background refresh tries to update from the API; if the network call fails, the cached Room data is still shown and search continues to work offline.

