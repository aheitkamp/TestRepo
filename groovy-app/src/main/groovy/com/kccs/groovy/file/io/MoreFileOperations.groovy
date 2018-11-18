package com.kccs.groovy.file.io;

def file = new File('c:/temp/newFile.txt')
//
//def contents =  file.text
//println contents
//
//// read line by line
//
//println ''
//def lines = file.readLines()
//println lines
//
//lines.forEach({line -> println line})

// word by word

file.readLines().each {
	line -> line.tokenize(' ').each { word -> println word}
}

