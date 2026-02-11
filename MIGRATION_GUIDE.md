# Guide de Migration - NewsApp vers Kotlin 2.1.0 + KSP + AGP 9.0

Ce guide vous aidera à utiliser le projet NewsApp rénové avec les dernières technologies.

## Prérequis

### Environnement de Développement

Avant de compiler le projet, assurez-vous d'avoir :

1. **Java Development Kit (JDK) 17 ou supérieur**
   - Télécharger : https://adoptium.net/
   - Vérifier : `java -version` (doit afficher version 17+)

2. **Android Studio Otter 3 Feature Drop ou supérieur**
   - Télécharger : https://developer.android.com/studio
   - AGP 9.0 nécessite cette version minimum

3. **Android SDK**
   - API 24 (Android 7.0) minimum
   - API 35 pour la compilation (installé via Android Studio)

## Changements Majeurs

### 1. KAPT → KSP

Le projet utilise maintenant KSP au lieu de KAPT pour le traitement des annotations.

**Avantages** :
- Compilation 25-40% plus rapide
- Moins de consommation mémoire
- Meilleur support pour Kotlin moderne

**Aucune action requise** : La migration est transparente pour le code source.

### 2. Kotlin 2.1.0

Le projet utilise Kotlin 2.1.0 avec toutes ses nouvelles fonctionnalités.

**Changements** :
- JVM Target 17 (au lieu de 1.8)
- Plugin Compose Compiler séparé
- Meilleures performances du compilateur

### 3. Android Gradle Plugin 9.0

AGP 9.0 intègre le support Kotlin nativement.

**Changements** :
- Le plugin `org.jetbrains.kotlin.android` n'est plus appliqué
- Kotlin est géré automatiquement par AGP
- Configuration simplifiée

## Instructions de Compilation

### Première Compilation

1. **Cloner ou mettre à jour le projet** :
   ```bash
   git pull origin main
   ```

2. **Ouvrir le projet dans Android Studio** :
   - File → Open → Sélectionner le dossier NewsApp
   - Attendre la synchronisation Gradle

3. **Configurer la clé API** :
   - Créer/modifier le fichier `local.properties` à la racine du projet
   - Ajouter : `API_KEY=votre_cle_api_ici`
   - (Obtenir une clé API sur https://newsapi.org/)

4. **Synchroniser Gradle** :
   - Android Studio devrait le faire automatiquement
   - Ou manuellement : File → Sync Project with Gradle Files

5. **Compiler le projet** :
   - Build → Make Project
   - Ou raccourci : Ctrl+F9 (Windows/Linux) / Cmd+F9 (Mac)

### Compilation en Ligne de Commande

```bash
# Nettoyer le projet
./gradlew clean

# Compiler le projet
./gradlew build

# Installer sur un appareil connecté
./gradlew installDebug

# Exécuter les tests
./gradlew test
```

## Résolution de Problèmes

### Erreur : "Unsupported class file major version"

**Cause** : Version Java incorrecte

**Solution** :
1. Vérifier la version Java : `java -version`
2. Installer JDK 17 si nécessaire
3. Dans Android Studio : File → Project Structure → SDK Location → JDK location

### Erreur : "SDK location not found"

**Cause** : Android SDK non configuré

**Solution** :
1. Ouvrir le fichier `local.properties`
2. Ajouter : `sdk.dir=/chemin/vers/Android/Sdk`
3. Ou laisser Android Studio le configurer automatiquement

### Erreur : "Plugin with id 'org.jetbrains.kotlin.android' not found"

**Cause** : Tentative d'utiliser l'ancien plugin Kotlin

**Solution** :
- Ce plugin n'est plus nécessaire avec AGP 9.0
- Vérifier que vous utilisez la dernière version du code

### Erreur de Synchronisation Gradle

**Solution** :
1. File → Invalidate Caches / Restart
2. Supprimer le dossier `.gradle` et `.idea`
3. Re-synchroniser le projet

## Optimisations Activées

Le projet bénéficie maintenant de plusieurs optimisations :

### Gradle
- ✅ Compilation parallèle
- ✅ Cache de configuration
- ✅ Configuration à la demande

### Kotlin
- ✅ Compilation incrémentale
- ✅ Suivi précis des changements Java
- ✅ KSP au lieu de KAPT

## Vérification de la Configuration

Pour vérifier que tout est correctement configuré :

```bash
# Afficher les informations du projet
./gradlew projects

# Afficher les dépendances
./gradlew :app:dependencies

# Vérifier la configuration
./gradlew :app:tasks
```

## Nouvelles Dépendances

Les versions suivantes sont maintenant utilisées :

| Bibliothèque | Version |
|--------------|---------|
| Kotlin | 2.1.0 |
| KSP | 2.3.5 |
| AGP | 9.0.0 |
| Gradle | 9.3.1 |
| Compose BOM | 2026.01.01 |
| Hilt | 2.59.1 |
| Retrofit | 2.11.0 |
| Timber | 5.0.1 |

## Support et Documentation

- **Documentation Kotlin** : https://kotlinlang.org/docs/home.html
- **Documentation KSP** : https://kotlinlang.org/docs/ksp-overview.html
- **Documentation AGP 9.0** : https://developer.android.com/build/releases/agp-9-0-0-release-notes
- **Guide de migration AGP 9.0** : https://blog.jetbrains.com/kotlin/2026/01/update-your-projects-for-agp9/

## Prochaines Étapes

Après avoir réussi à compiler le projet, vous pouvez :

1. Exécuter l'application sur un émulateur ou appareil physique
2. Exécuter les tests unitaires
3. Explorer les améliorations de performance
4. Continuer avec les phases suivantes de rénovation

---

**Date de Migration** : 11 février 2026  
**Version du Projet** : 1.0  
**Auteur** : Manus AI
