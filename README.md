# CurrencyConverter
Fancy currency converter app for android. 

App main architecture is based on MVVM. Main repository(RatesRepository) is responsible for getting data from LocaDataSource(Backed by room database) and RemoteDataSource(Backed by Retrofit api call). When app goes offline, app shows data from LocalDataSource. 

Tools and technologies: <br>
Koin for Dependency injection<br>
Kotlin Coroutines for threat management like api calling<br>
Retrofit for networking<br>
ViewPager2 for better efficiency<br>
Room for cache management<br>

Testing:<br>
For testing purpose the app used mockito, espresso and junit<br>


Edge cases considered while developing the app:<br>
1. If user select a currency and internet goes, calculate all currencies based on last updated data.<br>
2. Show user their last selected currency data when they go outside of the app and comes back with no internet connection. <br>
3. Cache last currency data to show it for user to guess instead of showing nothing. <br>
