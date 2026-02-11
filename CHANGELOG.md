# Changelog - NewsApp

Toutes les modifications notables de ce projet seront documentées dans ce fichier.

## [Non publié] - 2026-02-11

### Ajouté
- Migration de KAPT vers KSP pour améliorer les performances de compilation
- Plugin Compose Compiler pour Kotlin 2.0+
- Optimisations Gradle (cache, compilation parallèle, configuration à la demande)
- Support Java 17

### Modifié
- Mise à jour Kotlin 1.9.0 → 2.1.0
- Mise à jour Android Gradle Plugin 8.5.2 → 9.0.0
- Mise à jour Gradle 8.10 → 9.3.1
- Mise à jour Compose BOM 2024.11.00 → 2026.01.01
- Mise à jour Dagger Hilt 2.51.1 → 2.59.1
- Mise à jour Retrofit 2.9.0 → 2.11.0
- Mise à jour Timber 4.7.1 → 5.0.1
- Mise à jour Coil 2.4.0 → 2.7.0
- Mise à jour Mockito 3.12.4 → 5.15.2
- Mise à jour MockK 1.13.4 → 1.13.13
- Mise à jour JUnit Jupiter 5.8.2 → 5.11.4
- JVM Target 1.8 → 17

### Supprimé
- Plugin `org.jetbrains.kotlin.android` (intégré dans AGP 9.0)
- Configuration KAPT
- Bloc `composeOptions { kotlinCompilerExtensionVersion }`

### Améliorations de Performance
- Réduction attendue de 25-40% des temps de compilation grâce à KSP
- Compilation incrémentale optimisée
- Cache de configuration Gradle activé

## [1.0] - 2024-11-27

### Ajouté
- Mode offline avec synchronisation automatique
- Système de favoris
- SwipeRefresh pour rafraîchir manuellement les actualités
- Tests unitaires et d'intégration

### Fonctionnalités
- Liste d'actualités en temps réel
- Détails d'un article
- Stockage local avec Room
- Architecture Clean Architecture + MVVM
- Injection de dépendances avec Hilt
- Interface utilisateur avec Jetpack Compose
