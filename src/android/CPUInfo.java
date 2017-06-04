package com.leozhang.cpuinfo;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.ActivityManager;
import android.content.Context;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CPUInfo extends CordovaPlugin {
  public static final String TAG = "CPUInfo";

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if (action.equals("getMacAddress")) {
      callbackContext.success(this.getMacAddress());
    }else if(action.equals("getCpuName")) {
      callbackContext.success(this.getCpuName());
    }else if(action.equals("getCurCpuFreq")) {
      callbackContext.success(this.getCurCpuFreq());
    }else if(action.equals("getMinCpuFreq")) {
      callbackContext.success(this.getMinCpuFreq());
    }else if(action.equals("getMaxCpuFreq")) {
      callbackContext.success(this.getMaxCpuFreq());
    }else if(action.equals("getNumberCpuCores")) {
      callbackContext.success(this.getNumberCpuCores());
    }else if(action.equals("getRamTotalSize")) {
      callbackContext.success(this.getRamTotalSize());
    }else if(action.equals("getRamAvailableSize")) {
      callbackContext.success(this.getRamAvailableSize());
    }else if(action.equals("getSDTotalSize")) {
      callbackContext.success(this.getSDTotalSize());
    }else if(action.equals("getSDAvailableSize")) {
      callbackContext.success(this.getSDAvailableSize());
    }else if(action.equals("getRomTotalSize")) {
      callbackContext.success(this.getRomTotalSize());
    }else if(action.equals("getRomAvailableSize")) {
      callbackContext.success(this.getRomAvailableSize());
    }else if(action.equals("getTimes")) {
      callbackContext.success(this.getTimes());
    }else {
      return false;
    }
    return true;
  }
  // 获取CPU最大频率（单位KHZ）
  // "/system/bin/cat" 命令行
  // "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" 存储最大频率的文件的路径
  private String getMaxCpuFreq() {
    String result = "";
    ProcessBuilder cmd;
    try {
      String[] args = { "/system/bin/cat",
              "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };
      cmd = new ProcessBuilder(args);
      Process process = cmd.start();
      InputStream in = process.getInputStream();
      byte[] re = new byte[24];
      while (in.read(re) != -1) {
        result = result + new String(re);
      }
      in.close();
    } catch (IOException ex) {
      ex.printStackTrace();
      result = "";
    }
    return result.trim();
  }
  // 获取CPU最小频率（单位KHZ）
  private String getMinCpuFreq() {
    String result = "";
    ProcessBuilder cmd;
    try {
      String[] args = { "/system/bin/cat",
              "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" };
      cmd = new ProcessBuilder(args);
      Process process = cmd.start();
      InputStream in = process.getInputStream();
      byte[] re = new byte[24];
      while (in.read(re) != -1) {
        result = result + new String(re);
      }
      in.close();
    } catch (IOException ex) {
      ex.printStackTrace();
      result = "";
    }
    return result.trim();
  }
  // 实时获取CPU当前频率（单位KHZ）
  private String getCurCpuFreq() {
    String result = "N/A";
    try {
      FileReader fr = new FileReader(
              "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
      BufferedReader br = new BufferedReader(fr);
      String text = br.readLine();
      result = text.trim();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      result="";
    } catch (IOException e) {
      e.printStackTrace();
      result="";
    }
    return result;
  }
  // 获取CPU名字
  private String getCpuName() {
    try {
      FileReader fr = new FileReader("/proc/cpuinfo");
      BufferedReader br = new BufferedReader(fr);
      String text = br.readLine();
      String[] array = text.split(":\\s+", 2);
      for (int i = 0; i < array.length; i++) {
      }
      return array[1];
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
  // 获取CPU内核数量
  private int getNumberCpuCores() {
    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
      // Gingerbread doesn't support giving a single application access to both cores, but a
      // handful of devices (Atrix 4G and Droid X2 for example) were released with a dual-core
      // chipset and Gingerbread; that can let an app in the background run without impacting
      // the foreground application. But for our purposes, it makes them single core.
      return 1;
    }
    int cores;
    try {
      cores = new File("/sys/devices/system/cpu/").listFiles(CPU_FILTER).length;
    } catch (SecurityException e) {
      cores = -1;
    } catch (NullPointerException e) {
      cores = -1;
    }
    return cores;
  }

  private static final FileFilter CPU_FILTER = new FileFilter() {
    @Override
    public boolean accept(File pathname) {
      String path = pathname.getName();
      //regex is slow, so checking char by char.
      if (path.startsWith("cpu")) {
        for (int i = 3; i < path.length(); i++) {
          if (path.charAt(i) < '0' || path.charAt(i) > '9') {
            return false;
          }
        }
        return true;
      }
      return false;
    }
  };

  /**
   * 获得机身内存总大小
   *
   * @return
   */
  private String getRomTotalSize() {
    File path = Environment.getDataDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize = stat.getBlockSize();
    long totalBlocks = stat.getBlockCount();
    return blockSize * totalBlocks/1024+"";
  }

  /**
   * 获得机身可用内存
   *
   * @return
   */
  private String getRomAvailableSize() {
    File path = Environment.getDataDirectory();
    StatFs stat = new StatFs(path.getPath());
    long blockSize = stat.getBlockSize();
    long availableBlocks = stat.getAvailableBlocks();
    return blockSize * availableBlocks/1024+"";
  }
  //开机时间
  private String getTimes() {
    long ut = SystemClock.elapsedRealtime() / 1000;
    if (ut == 0) {
      ut = 1;
    }
    int m = (int) ((ut / 60) % 60);
    int h = (int) ((ut / 3600));
    return h + " : " + m;
  }

  private String getSDTotalSize() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      File sdcardDir = Environment.getExternalStorageDirectory();
      StatFs sf = new StatFs(sdcardDir.getPath());
      return sf.getBlockSize()*sf.getBlockCount()+"";
    }
    return "-1";
  }

  private String getSDAvailableSize() {
    String state = Environment.getExternalStorageState();
    if (Environment.MEDIA_MOUNTED.equals(state)) {
      File sdcardDir = Environment.getExternalStorageDirectory();
      StatFs sf = new StatFs(sdcardDir.getPath());
      return sf.getBlockSize()*sf.getAvailableBlocks()+"";
    }
    return "-1";
  }

  //mac地址
  private String getMacAddress() {
    String macSerial = null;
    String str = "";
    try {
      Process pp = Runtime.getRuntime().exec(
              "cat /sys/class/net/wlan0/address ");
      InputStreamReader ir = new InputStreamReader(pp.getInputStream());
      LineNumberReader input = new LineNumberReader(ir);


      for (; null != str;) {
        str = input.readLine();
        if (str != null) {
          macSerial = str.trim();// 去空格
          break;
        }
      }
    } catch (IOException ex) {
      // 赋予默认值
      ex.printStackTrace();
    }
    return macSerial;
  }

  private String getRamTotalSize(){//GB
    String path = "/proc/meminfo";
    String firstLine = null;
    int totalRam = 0 ;
    try{
      FileReader fileReader = new FileReader(path);
      BufferedReader br = new BufferedReader(fileReader,8192);
      firstLine = br.readLine().split("\\s+")[1];
      br.close();
    }catch (Exception e){
      e.printStackTrace();
    }
    if(firstLine != null){
      totalRam = (int)Math.ceil((new Float(Float.valueOf(firstLine) / 1024 ).doubleValue()));
    }
    return totalRam+"";
  }

  //获取可用运存大小
  private String getRamAvailableSize(){
    // 获取android当前可用内存大小
    ActivityManager am = (ActivityManager) this.cordova.getActivity().getSystemService(Context.ACTIVITY_SERVICE);
    ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
    am.getMemoryInfo(mi);
    //mi.availMem; 当前系统的可用内存
    return mi.availMem/1024+"";
  }


}

