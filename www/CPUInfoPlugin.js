var exec = require('cordova/exec');
/*jslint indent: 2 */
/*global window, jQuery, angular, cordova */
"use strict";

// Returns a jQuery or AngularJS deferred object, or pass a success and fail callbacks if you don't want to use jQuery or AngularJS
var getPromisedCordovaExec = function (command, success, fail) {
  var toReturn, deferred, injector, $q;
  if (success === undefined) {
    if (window.jQuery) {
      deferred = jQuery.Deferred();
      success = deferred.resolve;
      fail = deferred.reject;
      toReturn = deferred;
    } else if (window.angular) {
      injector = angular.injector(["ng"]);
      $q = injector.get("$q");
      deferred = $q.defer();
      success = deferred.resolve;
      fail = deferred.reject;
      toReturn = deferred.promise;
    } else if (window.when && window.when.promise) {
      deferred = when.defer();
      success = deferred.resolve;
      fail = deferred.reject;
      toReturn = deferred.promise;
    } else if (window.Promise) {
      toReturn = new Promise(function(c, e) {
        success = c;
        fail = e;
      });
    } else if (window.WinJS && window.WinJS.Promise) {
      toReturn = new WinJS.Promise(function(c, e) {
        success = c;
        fail = e;
      });
    } else {
      return console.error('CPUInfo either needs a success callback, or jQuery/AngularJS/Promise/WinJS.Promise defined for using promises');
    }
  }
  // 5th param is NOT optional. must be at least empty array
  exec(success, fail, "CPUInfo", command, []);
  return toReturn;
};

var CPUInfo=function(){};

CPUInfo.getMacAddress = function (success, fail) {
  return getPromisedCordovaExec('getMacAddress', success, fail);
};

CPUInfo.getCpuName = function (success, fail) {
  return getPromisedCordovaExec('getCpuName', success, fail);
};

CPUInfo.getCurCpuFreq = function (success, fail) {
  return getPromisedCordovaExec('getCurCpuFreq', success, fail);
};

CPUInfo.getMinCpuFreq = function (success, fail) {
  return getPromisedCordovaExec('getMinCpuFreq', success, fail);
};

CPUInfo.getMaxCpuFreq = function (success, fail) {
  return getPromisedCordovaExec('getMaxCpuFreq', success, fail);
};

CPUInfo.getNumberCpuCores = function (success, fail) {
  return getPromisedCordovaExec('getNumberCpuCores', success, fail);
};

CPUInfo.getRamTotalSize = function (success, fail) {
  return getPromisedCordovaExec('getRamTotalSize', success, fail);
};

CPUInfo.getRamAvailableSize = function (success, fail) {
  return getPromisedCordovaExec('getRamAvailableSize', success, fail);
};

CPUInfo.getSDTotalSize = function (success, fail) {
  return getPromisedCordovaExec('getSDTotalSize', success, fail);
};

CPUInfo.getSDAvailableSize = function (success, fail) {
  return getPromisedCordovaExec('getSDAvailableSize', success, fail);
};

CPUInfo.getRomTotalSize = function (success, fail) {
  return getPromisedCordovaExec('getRomTotalSize', success, fail);
};
CPUInfo.getRomAvailableSize = function (success, fail) {
  return getPromisedCordovaExec('getRomAvailableSize', success, fail);
};
CPUInfo.getTimes = function (success, fail) {
  return getPromisedCordovaExec('getTimes', success, fail);
};

module.exports = CPUInfo;