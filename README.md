# CurrencyConverter
Fancy currency converter app for android. 

App main architecture is based on MVVM. 

Tools and technologies: 
Koin for Dependency injection
Kotlin Coroutines for threat management like api calling
Retrofit for networking
ViewPager2 for better efficiency
Room for cache management

Testing:
For testing purpose the app used mockito, espresso and junit


Edge cases considered while developing the app:
1. If user select a currency and internet goes, calculate all currencies based on last updated data.
2. Show user their last selected currency data when they go outside of the app and comes back with no internet connection. 
3. Cache last currency data to show it for user to guess instead of showing nothing. 
