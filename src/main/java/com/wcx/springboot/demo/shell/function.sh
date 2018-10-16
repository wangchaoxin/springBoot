#!/bin/bash
#function defination,no need to add the "funcion" symbol
test(){
	#get the parameter
	echo $1
	if [ -n "$1" ];then
		echo $1
	fi
}
#call the function
test "first param"
#loop while
#while [ "$pa" != "yes" ]
#do
#	read pa
#	echo "continue"
#done
a=1
sum=0
while [ "$a" != "10" ]
do
	sum=$(($sum+$a))
	a=$(($a+1))
done
echo $sum
#for do done
sum=0
for ((i=1;i<=10;i=i+1))
do
	sum=$(($sum+$i))
done
echo $sum
#foreach loop
for x in a b c 
do
	echo $x
done
#check if the directory exist
dir=/hello
if [ $dir == "" ]|| [ ! -d $dir ];then
	echo "not a dir"
#	exit 1
fi
#loop the dir
files=`ls /wcx/shell`
for x in $files
do
	echo $x
done
