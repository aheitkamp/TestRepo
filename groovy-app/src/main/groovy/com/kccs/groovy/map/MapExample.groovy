package com.kccs.groovy.map

def map1 = [:]
map1.putAt('1', 'a')
println map1['1']
	
map1['2'] =  'b'
println map1['2']
				
map1 << ['3': 'c']
println map1['3']

// add & get from map where the method name? is the value
map1.xxx = 40
println map1.size()

println map1

println map1.xxx
