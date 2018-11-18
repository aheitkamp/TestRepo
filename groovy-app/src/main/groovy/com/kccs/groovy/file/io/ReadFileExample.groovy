package com.kccs.groovy.file.io;

def file = new File('c:/temp/newFile.txt')

println file.absolutePath
println file.size()

println "file ? ${file.isFile()}"
println "directory ? ${file.isDirectory()}"

def fileout = new File('c:/temp/newFilex.txt')

fileout.write('test\n')
fileout << 'line2'