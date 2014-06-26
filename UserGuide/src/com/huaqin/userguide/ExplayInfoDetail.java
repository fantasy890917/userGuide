package com.huaqin.userguide;

public class ExplayInfoDetail{
	
	
	
	//存储的信息
	public static int name_key = 0;
	public static int image_key = name_key +1;
	public static int number_key = name_key + 2;
	
	public static String key[] = {
		"base_name_key",
		"lockscreen_name_key",
		"starter_name_key",
		"notification_name_key",
		"application_name_key",
		"phonebook_name_key"
	};
	//创建需要显示的信息
	
	public static int init[][] ={
		{R.string.base_name,R.drawable.base_name,3},
		{R.string.lockscreen_name,R.drawable.lockscreen_name,2},
		{R.string.starter_name,R.drawable.starter_name,4},
		{R.string.notification_name,R.drawable.notification_name,4},
		{R.string.application_name,R.drawable.application_name,3},
		{R.string.phonebook_name,R.drawable.phonebook_name,6}
		
	};
	
	//显示每个单项的图片
	public static int initimage[][] ={
		{R.drawable.base1,R.drawable.base2,R.drawable.base3},
		{R.drawable.lockscreen1,R.drawable.lockscreen5},
		{R.drawable.start1,R.drawable.start2,R.drawable.start5,R.drawable.start6},
		{R.drawable.notifition1,R.drawable.notifition2,R.drawable.notifition3,R.drawable.notifition4},
		{R.drawable.application2,R.drawable.application3,R.drawable.application4},
		{R.drawable.call1,R.drawable.call2,R.drawable.call3,R.drawable.call4,R.drawable.call5,R.drawable.call6}
				
	};
	
	//显示每个单项需要的介绍信息
	public static int initstring[][] ={
		{
			R.string.base_produce_name1,
			R.string.base_produce_name2,
			R.string.base_produce_name3
		},
		{
			R.string.lock_produce_name1,
			R.string.lock_produce_name5			
		},
		{
			R.string.start_produce_name1,
			R.string.start_produce_name2,
			R.string.start_produce_name5,
			R.string.start_produce_name6,
		},
		{
			R.string.notifition_produce_name1,
			R.string.notifition_produce_name2,
			R.string.notifition_produce_name3,
			R.string.notifition_produce_name4,
		},
		{
			R.string.applicatin_produce_name2,
			R.string.applicatin_produce_name3,
			R.string.applicatin_produce_name4,
		},
		{
			R.string.call_produce_name1,
			R.string.call_produce_name2,
			R.string.call_produce_name3,
			R.string.call_produce_name4,
			R.string.call_produce_name5,
			R.string.call_produce_name6,
		},
	};
}
