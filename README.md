# Cordova AppVersion plugin

Reads the version of your app from the target build settings.

## Installation

### With cordova-cli

If you are using [cordova-cli](https://github.com/apache/cordova-cli), install
with:

    cordova plugin add cordova-plugin-app-version

### With plugman

With a plain [plugman](https://github.com/apache/cordova-plugman), you should be
able to install with something like:

    plugman --platform <ios|android> --project <directory> --plugin https://github.com/whiteoctober/cordova-plugin-app-version.git

### Manually in iOS

TODO: Write these instructions

### Manually in Android

TODO: Write these instructions

## Use from Javascript

If you are using jQuery, AngularJS, WinJS or any Promise/A library (Bluebird), promise style is supported. Use something like:

    cordova.CPUInfo.getMacAddress().then(function (address) {
        $('.address').text(address);
    });

If not, pass a callback function:

    cordova.CPUInfo.getMacAddress(function (address) {
        alert(address);
    });

In addition to the mac address  you can also retrieve other details about your application:


### getMacAddress

Returns the mac address of the mobile 

### getCpuName

Returns the name of cpu

### getCurCpuFreq

Returns the current frequency of cpu 

### getMinCpuFreq

Returns the min frequency of cpu

### getMaxCpuFreq

Returns the max frequency of cpu

### getNumberCpuCores

Returns the number of cpu cores

### getRamTotalSize

Returns the total size of ram

### getRamAvailableSize

Returns the available size of ram

### getSDTotalSize

Returns the total size of sd

### getSDAvailableSize

Returns the available size of sd

### getRomTotalSize

Returns total size of rom

### getRomAvailableSize

Returns available size of rom

### getTimes

Returns the start time of the mobile




