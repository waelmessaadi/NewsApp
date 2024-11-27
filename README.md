# Projet NewsApp

## Description du Projet

**NewsApp** est une application Android permettant de consulter les actualités en temps réel. Elle récupère des articles depuis une API, affiche les informations de manière claire et permet à l'utilisateur de consulter les détails d'un article en cliquant dessus.

## Architecture

L'application est construite en utilisant **Clean Architecture** et **MVVM (Model-View-ViewModel)** pour une gestion claire des responsabilités et une meilleure maintenabilité.

- **Clean Architecture** : Cette architecture permet de séparer les différentes couches de l'application (présentation, domaine, et données), facilitant ainsi les tests, la maintenance, et l'extension future du projet.
- **MVVM** : Le modèle **MVVM** (Model-View-ViewModel) est utilisé pour lier l'interface utilisateur à la logique métier via un **ViewModel**. Cela permet une gestion plus facile des états et des événements utilisateur avec l'utilisation de **StateFlow** et **SharedFlow** (pour les événements). Ces deux outils permettent une gestion réactive des données, où l'interface se met à jour de manière fluide en fonction des changements d'état dans le ViewModel.

### Pourquoi ces choix ?

- **Clean Architecture** : Elle offre une séparation nette entre la logique métier, la gestion des données et la présentation. Cela rend le projet plus facile à maintenir et à faire évoluer, tout en permettant une meilleure testabilité.
- **MVVM** : Le choix de MVVM est motivé par la nécessité d'avoir une couche de présentation qui puisse réagir aux changements d'état sans violer le principe de séparation des responsabilités.

## Choix des bibliothèques

### 1. **Kotlin Coroutines et Flow**

**Pourquoi Kotlin Coroutines et Flow ?**

Les **Kotlin Coroutines** sont utilisées pour simplifier la gestion de l’asynchronisme de manière concise et élégante. Avec **Flow**, nous avons pu gérer efficacement les flux de données, notamment pour récupérer les articles de news depuis l'API, tout en garantissant des mises à jour en temps réel de l'interface utilisateur. Cela permet également de gérer facilement les erreurs et l'état de chargement de l'application.

- **Coroutines** simplifie la gestion des tâches asynchrones et permet une exécution fluide et lisible du code sans bloquer le thread principal.
- **Flow** nous permet de travailler avec des flux de données asynchrones tout en ayant un contrôle total sur l'émission des valeurs et la gestion des erreurs.
- Bien que **RxJava** soit une technologie puissante et largement utilisée, **Coroutines** et **Flow** sont plus adaptées aux besoins spécifiques de notre projet grâce à leur simplicité, leur intégration native avec Kotlin, et leur gestion optimisée de la concurrence. Ils offrent une syntaxe plus fluide et sont mieux adaptés aux architectures modernes, ce qui simplifie la maintenance et améliore la lisibilité du code.


---

### 2. **Dagger Hilt**

**Pourquoi Dagger Hilt ?**

**Dagger Hilt** a été choisi pour l'injection de dépendances. Il permet de simplifier l'injection de dépendances en utilisant une approche modulaire et déclarative. 

- Hilt facilite l'injection dans les composants comme **ViewModels**, **UseCases**, et **Repositories** sans avoir à gérer manuellement la création de dépendances.
- Cette approche permet également d'améliorer la **testabilité** de l'application en rendant l'injection de dépendances simple et flexible.
- Je n'ai pas utilisé **Koin** dans ce projet car j'ai préféré une solution plus légère et native avec **Hilt**, qui s'intègre mieux avec l'architecture du projet et répond davantage à mes besoins en matière d'injection de dépendances.

---

### 3. **Retrofit et Gson**

**Pourquoi Retrofit et Gson ?**

**Retrofit** est une bibliothèque de client HTTP qui simplifie la communication avec les APIs REST. Elle est configurée avec **Gson** pour la sérialisation et la désérialisation des objets JSON en objets Kotlin.

- **Retrofit** simplifie l'intégration avec l'API et gère les appels réseau de manière performante en utilisant des **coroutines** pour des appels asynchrones.
- **Gson** est utilisé pour convertir les objets JSON renvoyés par l'API en objets Kotlin de manière fluide et automatique, en réduisant ainsi les risques d'erreurs de parsing.

## Problèmes Identifiés mais Non Résolus

- **Performance de l'API** : L'application pourrait bénéficier de tests de performance, notamment pour les appels réseau.
- **Gestion des erreurs réseau** : Bien que l'application gère certaines erreurs, il est possible d'améliorer la gestion des erreurs côté utilisateur (par exemple, affichage des erreurs réseau de manière plus détaillée).
- **Testes** : Manque de temps pour travailler sur les tests de manière approfondie. Bien que je ne sois pas encore expérimenté dans ce domaine, avec plus de temps, je pourrais améliorer considérablement la qualité et la couverture des tests.
  
## Temps de Travail

Le développement de cette application a duré **environ 20 heures de travail**. Ce temps a été consacré à la conception, à l’implémentation de l’architecture, à la gestion des appels réseau, à la mise en place des tests, ainsi qu’à l’optimisation de l’interface utilisateur.

