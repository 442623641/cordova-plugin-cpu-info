//
//  CPUInfo.h
//
//  Created by Zhang Leo on 2017/6/4.
//
//

#ifndef CPUInfo_h
#define CPUInfo_h


#endif /* CPUInfo_h */
#import <Cordova/CDVPlugin.h>

@interface CPUInfo : CDVPlugin

- (void)getMacAddress:(CDVInvokedUrlCommand*)command;

- (void)getCpuName:(CDVInvokedUrlCommand*)command;

- (void)getCurCpuFreq:(CDVInvokedUrlCommand*)command;

- (void)getMaxCpuFreq:(CDVInvokedUrlCommand*)command;

- (void)getNumberCpuCores:(CDVInvokedUrlCommand*)command;

- (void)getRamTotalSize:(CDVInvokedUrlCommand*)command;

- (void)getRamAvailableSize:(CDVInvokedUrlCommand*)command;

- (void)getRomTotalSize:(CDVInvokedUrlCommand*)command;

- (void)getRomAvailableSize:(CDVInvokedUrlCommand*)command;

- (void)getTimes:(CDVInvokedUrlCommand*)command;


@end
