# Table of Contents
- [Purpose of the Application](#purpose)
- [Tech Used](#tech)
- [API](#api)
- [Pages](#pages)
- [Screenshots](#screenshots)

## About the App

The Botany App is designed with the primary goal of simplifying the journey for entry-level botany enthusiasts. The application aims to streamline functionality by integrating only essential information to help users kickstart their exploration into the world of botany, the app architecture is simple and easily usable, having the pages of plants, their details and favourites.

## Tech Stack
-Navigation Graph (NavGraph):
Utilized for defining and managing the navigation flow within the application.

-Moshi:
Used for JSON parsing, facilitating efficient data conversion.

-Retrofit:
Employed for making network requests and handling API interactions.

-DataStore:
Utilized for data storage, providing a modern alternative to SharedPreferences.

-Dagger Hilt:
Implemented for dependency injection, enhancing the app's maintainability and scalability.

-Glide:
Integrated for efficient image loading and caching.

-Splash Screen API:
Implemented to enhance user experience during the application launch.

-OkHttp Logging Interceptor:
Used for logging HTTP interactions, aiding in debugging and performance monitoring.

-Firebase Authentication:
For secure user authentication.

-Room Database:
Utilized for storing and managing user-favorite plant items locally.

-MVI Architecture
Event based architecture


## API
- Mock API for plants list - https://65a7056e94c2c5762da627f6.mockapi.io/api/v1/plants
- Mock API for plants details - https://px5gxu4cxi.api.quickmocker.com/1

-Note: I couldn't find suitable APIs for the project and had to improvise, i used Mockapi.io and quickmocker.com

## Pages

- Welcome Page:
Upon launching the application, users are greeted with a welcome page that provides a seamless entry point.

- Authentication:
Pages for login, register and reset password.

- Home Page:
After successful authentication, users are redirected to the home page. The home page features two prominent buttons: one for logging out and another for navigating to the plant list.

- Plant List:
In the list fragment, users can explore a list of flowers sourced from the from the API. Clicking on a specific item in the list will lead the user to the details page for that particular plant.

- Details Page:
On the details page, users can find comprehensive information about the selected plant.

- Favourites Page:
On the favourites page users can find the list of their preferred plants.


## Screenshots
![welcome](https://github.com/gugatevzadze/Project_1/assets/123153999/781dfac4-659f-45bd-a879-93db46a415c5)
![register](https://github.com/gugatevzadze/Project_1/assets/123153999/a854ff13-3645-456c-8723-b5ba919d2e49)
![login](https://github.com/gugatevzadze/Project_1/assets/123153999/200c0ba6-7243-4cf7-97e3-631bfcaf46c6)
![reset](https://github.com/gugatevzadze/Project_1/assets/123153999/34d9f5ea-5c49-46ae-82d6-603d95019fb2)
![home](https://github.com/gugatevzadze/Project_1/assets/123153999/0df35f9d-acb0-4629-996a-f366c8c2e2e8)
![list](https://github.com/gugatevzadze/Project_1/assets/123153999/ea4554ce-c5d5-4ebc-92a3-3ca8948953f9)
![detail](https://github.com/gugatevzadze/Project_1/assets/123153999/f7bfdf41-eafb-4eb3-93af-cc76f5f8f5f8)
![favourites](https://github.com/gugatevzadze/Project_1/assets/123153999/a382bf6b-aa9b-42be-92a0-187ac8d3dcaf)

so far these were the features I was able to implement, in the refarctor branch I plan to add several pages for different topics, such as tips for gardening, articles page, etc. and also create more sophisticated UI


