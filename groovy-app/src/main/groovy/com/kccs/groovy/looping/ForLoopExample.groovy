package com.kccs.groovy.looping

for(x=0; x<10; x++) {
	println x
}

def x = 10
while(x<20) {
	println x++
}

def myList = [1,2,3,4,5,6]
for (int i : myList) {
	println "value of var = $i"
}

1.upto(10) {  
	println "upto value of var = $it"
}

0.step(6, 2) {
	println "step value of var = $it"
}

5.times {  
	println "time value of var = $it"
}
