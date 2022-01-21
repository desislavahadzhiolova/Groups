# Groups
Android Development with Kotlin Project 
The initial idea of Groups was to be a social app that allows users to share photos and updates. During the development of the idea changed – now it is a “diary app”, that allows a user to create posts with associated photos.
The user can Add a Post (containing a Title, Description, and Images) and see a list of created post. Posts can be edited, updated, and deleted. 
To gain access to the app, the user must log in. There are two different ways to do so: To register via email or to use an existing Google account to sign in. 
In terms of persistence the app has gone through the following modifications: Temporary Storage -> Parcels and or/Bundles -> Json/Local Database (Authentication) -> Cloud Support: Basic + Google Authentication via Firebase – connected to a relative database.
The App navigation is achieved via Menus, there is a Number picker during the Sign-Up process to select age, that is sadly bugged, most likely by a layout issue. I have used the same code for integrating a Number Picker in a blank app and it is working – which I will showcase during the demo. 
In terms of DX the following is present: Data Validation, Adherence to Android Best Practices, UI & Material Design Guidelines adhered to, Design Patterns Applied (MVP/MVVM).
There are two branches: main - where the readme is and master, where the code is.
Thank you for reading the README file, I hope you will like the app,
Desislava.





