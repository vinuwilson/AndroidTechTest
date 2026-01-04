# Android Tech Test

## Description

A sample app that showcase a list of comments, built in Kotlin using Jetpack Compose and architecture components, following Clean Code principles and modularization.

## Project Specification

## 1. Tech Stack

* Kotlin
* Jetpack Compose for the UI
* Hilt for DI
* Coroutines and Flow - for making asynchronous calls
* Retrofit - for networking
* mockk - for unit testing

## API

* https://jsonplaceholder.typicode.com/comments

## 2. Features

* Home Screen - Home screen that displays a list of comments retrieved from the API.
* Details Screen - Detail screen that displays more information about the comments.

## 3. Architecture & Design Pattern

* SOLID Principles - The app is designed following the SOLID principles to ensure a scalable and maintainable codebase.
* Clean Code Architecture - The app architecture is designed to be clean and modular, separating concerns into different layers (e.g., UI, domain, data).
* Modular - The app codebase is organized into feature-based modules, ensuring improved maintainability, flexibility, and scalability.
* Design Pattern - The application is developed using Test-Driven Development (TDD) and follows the Model-View-ViewModel (MVVM) design pattern.

## 4. Implementation

* Type-Safe Navigation - The Navigation Component is used with type-safe arguments to navigate between screens.

## Further enhancements

* Support landscape orientation and tablets.
* Offline mode - Support offline mode.
* Furthermore cosmetics and refactoring is an endless thought.

## Snapshots