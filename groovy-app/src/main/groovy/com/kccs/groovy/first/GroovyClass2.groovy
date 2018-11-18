package com.kccs.groovy.first

class GroovyClass2 {
	
	String name
	String place
	
	def methodOne() {
		println "hello world $name $place"
	}
	
	GroovyClass2(name, place) {
		this.name = name
		this.place = place
	}
	
	def String methodTwo() {
		"hello world $name $place"
	}

}
