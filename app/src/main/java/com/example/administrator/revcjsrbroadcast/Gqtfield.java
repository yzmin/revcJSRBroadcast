package com.example.administrator.revcjsrbroadcast;

/**
 * Created by Administrator on 2017/1/17.
 */

public class Gqtfield {
    //LED
    /**
     * @see EXTRA_LED_COLOR
     * @see EXTRA_LED_LEVEL
     * @see EXTRA_LED_BRIGHT_TIME
     * @see EXTRA_LED_OFF_TIME
     */
    public static final String ACTION_CONTROL_LED = "com.zed3.action.CONTROL_LED";

    /**
     * 当 @see EXTRA_LED_BRIGHT_TIME 与 @see EXTRA_LED_OFF_TIME 的值均为0时则表示LED指示灯为常亮状态，否则为闪烁
     * 必须输入的extra字段
     * @see EXTRA_LED_LEVEL
     */
    public static final String ACTION_CONTROL_LED_OFF = "com.zed3.action.CONTROL_LED_OFF";


    /**
     * This is String extra, default value is null
     * Indicator color, format HEX, for example: # FF0000 (red)
     */
    public static final String EXTRA_LED_COLOR = "com.zed3.extra.led.COLOR";
    /**
     * This is int extra, default value is -1.
     * Indicator Level
     */
    public static final String EXTRA_LED_LEVEL = "com.zed3.extra.led.LEVEL";
    /**
     * This is int extra, default value is 0
     * Indicator on time
     */
    public static final String EXTRA_LED_BRIGHT_TIME = "com.zed3.extra.led.BRIGHT_TIME";
    /**
     * This is int extra, default value is 0
     * The time that the indicator light is off
     */
    public static final String EXTRA_LED_OFF_TIME = "com.zed3.extra.led.OFF_TIME";


    //PTT键
    public static final String ACTION_PTT_DOWN = "com.zed3.action.PTT_DOWN";
    public static final String ACTION_PTT_UP = "com.zed3.action.PTT_UP";
    //组编号
    public static final String ACTION_SWITCH_PTT_GROUP = "com.zed3.action.ACTION_SWITCH_PTT_GROUP";
    public static final String EXTRA_PTT_GROUP_INDEX = "PTT_GROUP";
    //紧急告警键
    public static final String ACTION_EMERGENCY_DOWN = "com.zed3.action.ALARM_EMERGENCY_DOWN";
    public static final String ACTION_EMERGENCY_UP = "com.zed3.action.ALARM_EMERGENCY_UP";
    //自定义按键
    public static final String ACTION_CUSTOM_DOWN = "com.zed3.action.CUSTOM_KEY_DOWN";
    public static final String ACTION_CUSTOM_UP = "com.zed3.action.CUSTOM_KEY_UP";
    //自定义按键2
    public static final String ACTION_CUSTOM2_DOWN = "com.zed3.action.CUSTOM2_KEY_DOWN";
    public static final String ACTION_CUSTOM2_UP = "com.zed3.action.CUSTOM2_KEY_UP";
    //GPS
    public static final String ACTION_DISABLE_GPS = "com.zed3.action.ACTION_DISABLE_GPS";
    public static final String ACTION_ENABLE_GPS = "com.zed3.action.ACTION_ENABLE_GPS";
    //旋钮
    public static final String ACTION_SWITCH_PTT_GROUP_LEFT = "com.zed3.action.SWITCH_PTT_GROUP_LEFT";
    public static final String ACTION_SWITCH_PTT_GROUP_RIGHT = "com.zed3.action.SWITCH_PTT_GROUP_RIGHT";

}
