# Ciné Baptiste 🎬

Application Android native développée en Kotlin, démontrant une architecture moderne et modulaire pour consulter les films populaires.

## 🏗 Architecture

Le projet respecte les principes de la **Clean Architecture** et sépare les responsabilités en trois couches distinctes :

### 1. Domain Layer (`com.example.epreuvebaptiste.domain`)
Le cœur de l'application, indépendant de tout framework Android.
- **Models** : Objets métier purs (`Movie`).
- **Repository Interface** : Contrats définissant les opérations (`MovieRepository`).

### 2. Data Layer (`com.example.epreuvebaptiste.data`)
Responsable de la récupération des données.
- **Remote** : Utilisation de **Ktor** pour les appels HTTP vers l'API TMDB.
- **DTOs** : Objets de transfert de données et Mappers (`toDomain()`) pour convertir le JSON en objets métier.
- **Repository Implementation** : Implémentation concrète qui gère la logique d'appel et la gestion d'erreurs (Fallback Mock).

### 3. UI Layer (`com.example.epreuvebaptiste.ui`)
Interface utilisateur construite avec **Jetpack Compose**.
- **Pattern MVI** (Model-View-Intent) :
    - `MoviesIntent` : Actions de l'utilisateur (ex: Click, Load).
    - `MoviesState` : État unique de l'écran (Loading, Success, Error).
    - `MoviesViewModel` : Gère la logique et expose l'état via `StateFlow`.

## 🛠 Stack Technique

- **Langage** : Kotlin
- **UI** : Jetpack Compose, Material3
- **Injection de Dépendances** : Koin
- **Réseau** : Ktor Client (ContentNegotiation, Serialization)
- **Image Loading** : Coil
- **Async** : Coroutines & Flow
- **Hardware** : Gestionnaire de son (`SoundManager`) pour feedback haptique/audio.

## 🚀 Fonctionnalités

- Affichage de la liste des films populaires (API TheMovieDB).
- Gestion des erreurs réseau et chargement.
- Feedback sonore au clic sur un élément.