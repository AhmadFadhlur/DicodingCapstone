-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
-keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

-keep class com.ashoka.core.** {*;}
-dontwarn com.ashoka.core.**
-keepattributes Exceptions, Signature, InnerClasses


-dontobfuscate
