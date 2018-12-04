#!/bin/bash 
# comment 
echo 'this is a testSoftReference'

#whether last command is excuted successfully  0 success
echo $?  
#path
PATH=$PATH:~/bin
echo $PATH
export PATH

#can use $? to get the excute result of the script
#exit 0   

#read some char
#read
#read -p "enter a number" a
#echo $a 

#date function ,used to define different file name
dateParam=`date +%Y%m%d`
#dateParam2=`date --date="YYYYMMDD" +%s`
echo $dateParam
echo "dateParam2 $dateParam2"

#testSoftReference file or directory
test -e /wcx && echo "file exist"||echo "file not exist" #whether exist file
test -f /wcx && echo "is a file" ||echo "not a file" #whether is a file
test -d /wcx && echo "is a directory"||echo "not a directory" #whether is a directory
# testSoftReference number
test 1 -eq 2 && echo "1 euqal 2"||echo "1 not equal 2" #number equal
test 1 -ne 2 && echo "1 not euqal 2"||echo "1 equal 2" #number not equal
test 1 -gt 2 && echo "1 greater than 2"||echo "1 less than 2" #number greater than
test 1 -lt 2 && echo "1 less than 2"||echo "1 less than 2" #number less than 
test 1 -ge 2 && echo "1 greater than equal 2"||echo "1 less than equal 2" #number greater than equal
test 1 -le 2 && echo "1 less than equal 2"||echo "1 greater than equal 2" #number less than equal 
#testSoftReference string
test -z "" && echo "empty string" ||echo "not empty string"  #whether string is empty
test -n "" && echo "not empty string" ||echo "empty string"  #whether string is not empty
test "str1" = "str2" && echo "str1 equal str2" ||echo "str1 not equal str2"
test "str1" != "str2" && echo "str1 not equal str2" ||echo "str1 equal str2"
#and or !   -a  and   -o or  !  not
test -n "1" -a -n "1" && echo "and -a" 
test -n "1" -o -n "1" && echo "or -r"
test ! -n "" && echo "! not"

#[] must space for ==  -a and
[ -z "" ] && echo success #if zero
[ "" == "" ] && echo "=="||echo "!="
[ "1" == "1" -a "2" == "2" ] && echo "succ"||echo "fail"
#parameters $0 represens the file name
echo "parameter0 $0"
[ -n $1 ]&&echo "parameter1 $1"

# if then &&and  ||or
param1="2"
echo " param1 $param1"
if [ "2" == "1" ] && [ "2" == "2" ];then
	echo "if"
else
	echo "else"
fi

#if else if else
if [ "$param1" == "1" ];then
	echo "if"
elif [ "$param1" == "2" ];then
	echo "else if"
else
	echo "else"
fi

#case: * represents default
a="start"
a="end"
a="testSoftReference"
case $a in 
"start")
	echo "start"
	;;
"end")
	echo "end"
	;;
*)
	echo "default"
esac
