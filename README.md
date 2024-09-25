# Instant Messaging Mobile App


This mobile application enables users to communicate with friends in real-time through an intuitive and easy-to-use interface. Whether logging in or registering a new account, users can quickly connect and begin chatting.

# Key Features
* User Authentication: Securely log in or register using a username and password.
* Friend Management: Add friends by username and manage your contact list.
* Real-Time Messaging: Engage in live conversations with your contacts.
* Push Notifications: Get notified when you receive new messages (ensure notifications are enabled on your device).


# How to Use the App


# 1. Login Page
Upon launching the app, you will be greeted by the Login Page, where you can enter your username and password

![Screenshot (95)](https://github.com/user-attachments/assets/7b959743-6880-43c8-b5a9-3b46ce9fc93e)


New User? Click the "Click here" link located beneath the login button to register a new account.

# 2. Registration Page
On the Registration Page, fill in the required fields—username, email, and password—to create a new account.

![Screenshot (100)](https://github.com/user-attachments/assets/3cfa3947-099c-4636-afbf-12a50cc2edac)

Once registration is successful, you'll be automatically redirected to the Login Page to log in with your newly created credentials.


# 3.  Dashboard
After logging in, you will arrive at the Dashboard, where you can manage your friends, check your recent conversations, and start a new chat.

![Screenshot (96)](https://github.com/user-attachments/assets/49434c53-00f4-4d60-973a-d1b54628a1f6)

# 4. Adding Friends
To add new friends, click on the "plus" icon located at the bottom-right corner of the screen.

![Screenshot (97)](https://github.com/user-attachments/assets/34b3436f-89c2-46e5-8380-e76b6a6a1f41)

Enter your friend's username in the search bar and press the "Add" button to send a friend request.

![Screenshot (98)](https://github.com/user-attachments/assets/a12324fe-044a-409f-b0ee-78f3febb38e4)

Once your friend is added, you can begin chatting with them immediately.

# 5. Chatting Interface
Once you've added a friend, you can start chatting by selecting their name from your friends list.

![Screenshot (99)](https://github.com/user-attachments/assets/d2a02a43-853f-409d-ab22-472e8e648100)


# Server Setup Instructions

To run the app, you'll need to set up the server by following the instructions provided in the ourServer repository.
# Steps to Start the Server:
1. Clone the [ ourServer](https://github.com/sondoszo25/MessagingApp-ServerSide.git) repository.
2. Follow the instructions in the README of the "MessagingApp-ServerSide" repository to install dependencies and start the server.
3. Ensure that Firebase is correctly connected.


# Technologies Used
This app leverages several modern technologies to provide a robust and scalable solution for real-time messaging:

* React Native: Used for building the cross-platform mobile app (iOS & Android).
* Node.js: Backend server running JavaScript for handling API requests and routing.
* Express.js: Lightweight Node.js framework for building server-side logic.
* MongoDB: NoSQL database used to store user credentials and chat data.
* Firebase: Used for real-time messaging and handling push notifications.
* Socket.IO: Enables real-time, bi-directional communication between the client and server.
* JWT (JSON Web Tokens): For securing user authentication and authorization.
* Git: Version control system for managing the codebase.
