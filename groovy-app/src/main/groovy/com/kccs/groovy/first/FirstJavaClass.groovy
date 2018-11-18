package com.kccs.groovy.first

class FirstJavaClass {

	def name			// dynamic typing
	int myInt = 1		// static typing
	def myMap = []
	
	static void main(String[] args) {
		
		int x = 5
		def y = 5
		println y
		y = "hello"
		println y
	}

	 String success() {
		return "Hello $name $myInt"
	}
}
