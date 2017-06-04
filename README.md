# Cordova cpu info plugin

Reads the cpu info of your mobile.

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




