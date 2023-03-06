# RECIPES APP

![Course](https://camo.githubusercontent.com/43f1140f4f5cf05334af18d88ad07118121fc7bed1aec36c581337e8fc941599/68747470733a2f2f692e706f7374696d672e63632f36707430475435342f5468756d626e61696c2d312e706e67)

## 🤖 Summary
- Fully Functional Recipes Food app in Kotlin with XML.
- Based on:
    - **Teacher:** Stefan Jovanovic.
    - **Link Course Udemy:** [Modern Food Recipes App - Android Development with Kotlin](https://www.udemy.com/course/modern-food-recipes-app-android-development-with-kotlin/)

## 💼 Libraries
- Room
- Navigation Component
- Hilt
- Retrofit
- LiveData
- Flow
- Data Store Preferences
- ViewModel
- ViewPager2
- Coil

## 🫡 Main Features
- Display List of Recipes (Get from Third party API and save this into Local DB)
- Save Favorites Recipes into Local DB
- Search / Filter Recipes => Keep filters selected in Data Store
- 

## 🔥 Main Topics
- Implement Offline Cache strategy. 
- Dark & Light Theme with XML 
- Motion Layout
- Create Contextual Action Menu
- Show Modal Bottom Sheet

## 🤔 Also learning about...
- Configure Toolbar and Navigation sync
- Use @Volatile and synchronized to create our Local DB Instance 
  - @Volatile => Make our variable immediately available in another threads
  - synchronized() => Lock the process to another threads until it finishes
  - We use both to ensure that our Database instance will be created only once.
- Create custom Type Converter in Room
- Use DiffUtil to improve performance in our recycler view.
- Use FragmentContainerView instead of fragment tag in out Navigation Component.
- Use Coil to display placeholder and error images
- Internet Connection Observer

## 🐙 Changes
- Instead of use GsonConverterFactory, I use Kotlin Serialization
- Instead of make cache strategy in viewModel, I made it in repository following Single Source of 
  truth principle

## 🤕 Problem Resolve


## 💡 Ideas
- Make Filter available even when is offline, with data cached.