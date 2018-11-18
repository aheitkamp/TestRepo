package com.kccs.groovy.array

def myArray = new String[3] 
myArray[0] = "a"
myArray[1] = "b"
myArray[2] = "c"

def String[] myArray2 = ["x", "y", "x"]

0.upto(myArray.length - 1) {  
	println "upto value myArray = " + myArray[it]
	println "upto value myArray2 = " + myArray[it]
}