# DrawOverApps
Cordova Plugin for android Apps that draw over the other apps permission.

# To install the plugin into your application 
```ruby
ionic cordova plugin add https://github.com/basheerkohli/DrawOverApps.git --save
```

# How to use: 
Declare cordova variable.
```ruby
declare var cordova: any;
```

# To check if the permission is enabled 

```ruby
cordova.plugins.DrawOverApps.checkPermission(
      null,
      resultCode => {  // success callback
        if (resultCode == 0) {  // 0 - permission given.
         //....
        }
      },
      resultCode => { // failure call back
        if (resultCode == -1) { //-1 need to give permission
        //....
        }
      }
    );
```

# To request the permission

```ruby
cordova.plugins.DrawOverApps.requestPermission(
              null,
              resultCode => {
                if (resultCode == 0) {
                    // permission granted
                }
              },
              errorCode => {
                if (errorCode == -1) {
                  // permission denied
                }
              }
            );
```
