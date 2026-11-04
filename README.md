# 🎬 Ciné Baptiste

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple?style=for-the-badge&logo=kotlin)
![Android](https://img.shields.io/badge/Platform-Android-green?style=for-the-badge&logo=android)
![Compose](https://img.shields.io/badge/UI-Jetpack_Compose-blue?style=for-the-badge&logo=jetpackcompose)
![License](https://img.shields.io/badge/License-MIT-orange?style=for-the-badge)

**Ciné Baptiste** est une application Android moderne permettant de consulter les films populaires du moment. 
Le projet met un point d'honneur sur la **Qualité Logicielle**, l'**Architecture Modulaire** et l'**Expérience Utilisateur**.

---

## 🏗 Architecture & Conception

Le projet suit rigoureusement les principes de la **Clean Architecture** (Onion Architecture) pour garantir la séparation des préoccupations (SoC) et la testabilité.

### 📐 Vue d'ensemble des couches

```text
com.example.epreuvebaptiste
├── data                # [COUCHE DATA] : Infrastructure
│   ├── remote          # API Config & DTOs
│   └── repository      # Implémentation du repository
│
├── di                  # [DEPENDENCY INJECTION]
│   └── KoinModule.kt   # Modules Koin (AppModule)
│
├── domain              # [COUCHE DOMAIN] : Cœur métier
│   ├── models          # Objets métier (movie.kt)
│   ├── repository      # Interfaces (Contrats)
│   └── usecase         # Logique métier (GetPopularMoviesUseCase)
│
├── manager             # [HARDWARE ABSTRACTION]
│   └── SoundManager.kt # Gestion Audio & Vibration
│
├── ui                  # [COUCHE PRESENTATION]
│   ├── components      # Design System (MovieCard.kt)
│   ├── movies          # Feature principale (Screen, VM, Contract)
│   ├── navigation      # Gestion de la navigation (NavHost)
│   └── theme           # Thème de l'application
│
├── util                # [UTILITAIRES]
│   └── Extensions.kt   # Extensions Kotlin (Context.showToast)
│
├── MainActivity.kt     # Point d'entrée (Single Activity)
└── MoviesApp.kt        # Classe Application (Init Koin)
```

### 🔄 Pattern UI : MVI (Model-View-Intent)

L'interface réagit de manière **unidirectionnelle** (UDF) :
1.  **INTENT** : L'utilisateur effectue une action (ex: `OnMovieClicked`).
2.  **PROCESSING** : Le ViewModel traite l'action via le UseCase.
3.  **STATE** : Le ViewModel émet un nouvel état immuable (`MoviesState`).
4.  **RENDER** : L'UI se redessine automatiquement.

---

## 🛠 Stack Technique

*   **Langage** : [Kotlin](https://kotlinlang.org/) (100%)
*   **UI Toolkit** : [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material3)
*   **Injection de Dépendances** : [Koin](https://insert-koin.io/) (Léger & Kotlin-centric)
*   **Réseau** : [Ktor Client](https://ktor.io/)
    *   Content Negotiation (JSON)
    *   Serialization
*   **Architecture** : Clean Arch + MVVM/MVI + Repository Pattern
*   **Chargement d'Images** : [Coil](https://coil-kt.github.io/coil/)
*   **Navigation** : Jetpack Navigation Compose

---

## ✨ Fonctionnalités & "Savoir-Faire"

### 📱 Interface Utilisateur (UI/UX)
*   **Design System** : Utilisation de composants atomiques ("LEGO") réutilisables (ex: `MovieCard`).
*   **États d'écran** : Gestion explicite des états `Loading`, `Error`, `Empty`, et `Content`.
*   **Internationalisation** : Support complet **Français 🇫🇷** / **Anglais 🇺🇸**.

### 🔌 Accès API & Résilience
*   Intégration de l'API **The Movie Database (TMDB)**.
*   **Système de Fallback** : Si le réseau échoue, l'application bascule automatiquement sur un jeu de données Mocké (Faux films) pour ne jamais laisser l'utilisateur devant un écran vide.

### 📳 Intégration Hardware (Capacités Natives)
Utilisation d'un `Manager` dédié pour interagir avec le matériel via le `Context` :
*   **Audio** : Feedback sonore lors du clic.
*   **Haptique** : Vibration physique lors de l'interaction.
*   **Système** : Utilisation des Toasts Android via une fonction d'extension (`Context.showToast`).

---

## 🚀 Installation & Démarrage

1.  **Pré-requis** : Android Studio Koala (ou plus récent), JDK 17+.
2.  **Cloner le dépôt** :
    ```bash
    git clone https://github.com/BaptisteNodon/EpreuveBaptisteKotlin.git
    ```
3.  **Configuration API** :
    *   Une clé API TMDB de test est déjà incluse dans `data/remote/ApiConfig.kt` pour faciliter l'évaluation.
4.  **Compiler** : Ouvrir le projet et lancer `Run 'app'`.

---

## 👤 Auteur

Projet réalisé par **Baptiste** dans le cadre de l'évaluation technique Android.
*Objectif : Validation des compétences d'architecture, UI et intégration native.*
