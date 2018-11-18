package com.kccs.groovy.list

def myList = [1,2,3,4,5]
println  myList.size()

def myList2 = new ArrayList<String>()
myList2[0] = 'x'
myList2.add('y')
myList2 << 'z'
myList2.push('push')

println myList2

myList2.putAt(1,'a')
println myList2
println myList2.get(2)

println myList2[-1]		//Last value
println myList2[-2]		//2nd last value

myList2.remove('z')
println myList2

//myList2 = myList2 - 'push'
//println myList2

myList2.removeIf({i -> i == 'x'})
println myList2

// count same kind in list
def myList3 = [1,2,3,4,5,4,3]
def myList4 = [1,2,3,4,5,4,3]
println myList3.count(4)
println myList3.count {x -> x > 4}

myList4.sort()


