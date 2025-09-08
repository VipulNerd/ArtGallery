# Art Gallery App

## Overview
Art Gallery is an Android application built using **Jetpack Compose** that allows users to browse artwork images, view details, and manage a shopping cart. The app demonstrates modern UI/UX design, dynamic navigation, and a fully themed experience using **Material3**.

## Features
- **Gallery View:** Display artwork images with descriptions, navigation buttons (Prev/Next), and dynamic titles.
- **Details Screen:** View detailed information about selected artwork, including description, price, and favorite toggle.
- **Add to Cart:** Users can add artwork to the cart with proper quantity management.
- **Cart Screen:** View cart items, update quantities, remove items, and see total price.
- **Favorites:** Toggle favorite status for artworks.
- **Material3 Theme:** Consistent theming with primary and secondary colors, rounded buttons, cards, and polished typography.
- **Navigation:** Bottom navigation with Home, Cart, and Logout options.

## Technologies
- **Kotlin**
- **Jetpack Compose**
- **Material3**
- **Android ViewModel**
- **Navigation Compose**

## Architecture
- `ArtGalleryAppMain.kt` / `ArtGalleryLayout.kt`: Main screen displaying gallery and artwork cards.
- `ArtGalleryView.kt`: Reusable composable for displaying a single artwork with Add to Cart and favorite button.
- `CartScreen.kt`: Screen to manage cart items.
- `DetailsScreen.kt`: Shows detailed information of an artwork.
- `CartViewModel.kt`: Handles cart state and item quantities.
- `ArtGalleryViewModel.kt`: Handles gallery state and current image navigation.

## Installation
1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or physical device running Android 12+.

## Usage
1. Launch the app.
2. Browse artworks using Prev/Next buttons.
3. Tap "Add to Cart" to add items to the cart.
4. Tap "View Details" for more information.
5. Navigate to the cart using the bottom navigation bar.
6. Update quantities or remove items in the cart.
7. Toggle favorite artworks using the heart icon.

## Screenshots
<img width="458" height="797" alt="Screenshot 2025-09-08 at 4 32 07 PM" src="https://github.com/user-attachments/assets/9b69a5ac-43bf-49db-9119-7810e70872cd" />
<img width="458" height="786" alt="Screenshot 2025-09-08 at 4 32 20 PM" src="https://github.com/user-attachments/assets/fd783af4-d0af-43be-a3d9-2589357e89c5" />
<img width="458" height="786" alt="Screenshot 2025-09-08 at 4 32 52 PM" src="https://github.com/user-attachments/assets/f7c913c2-04d3-4c2a-aac2-7538bba41222" />
<img width="458" height="786" alt="Screenshot 2025-09-08 at 4 34 01 PM" src="https://github.com/user-attachments/assets/b02ae943-b65a-4b39-aefb-df46c017326a" />
<img width="458" height="786" alt="Screenshot 2025-09-08 at 4 34 21 PM" src="https://github.com/user-attachments/assets/b52d9fe4-865b-4f9d-80bb-88d7d9c9462b" />


## Future Improvements
- Integrate real artwork images from a remote API.
- Implement user authentication.
- Add checkout and payment functionality.
- Improve animations and transitions between screens.

## License
This project is licensed under the MIT License.
