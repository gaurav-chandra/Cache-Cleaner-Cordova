CacheCleaner plugin for phonegap 3
==================================

PhoneGap 3 plugin to clear cache on Android. 

Install
========
Assuming the PhoneGap CLI is installed, from the command line run:
```
phonegap local plugin add https://github.com/sagittaros/CacheCleaner.git
```
After that, paste this in your config.xml: 
```
<feature name="CacheCleaner">
<param name="android-package" value="org.felixtioh.phonegap.plugins.cachecleaner.CacheCleaner"/>
</feature>
```
Also include cache_cleaner.js in your index.html:
```
 <script type="text/javascript" src="js/cache_cleaner.js"></script>
```

You can clean you cache by using:
```
cache_cleaner.delete();
```

Example:
```
<button onclick="cache_cleaner.delete();">delete</button>
```
