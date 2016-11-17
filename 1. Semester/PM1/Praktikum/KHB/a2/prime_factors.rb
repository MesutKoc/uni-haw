# defines a number N whose prime factors will be found
 
N = 100
isitprime = 0
 
# generates array of primes up to the square root of N
 
primes = [2,3]
 
(4..Math.sqrt(N).floor).each do |i|
(0..primes.length-1).each do |j|
if i % primes[j] == 0
isitprime = 0
break
else
isitprime = 1
end 
end
if isitprime == 1
primes << i
end
end
 
puts primes
# tests divisibility into N and divides out factors
 
n = N
factors = []
 
while n > 1
 
# tests for prime factors smaller than the square root of N
 
(0..primes.length-1).each do |k|
if n % primes[k] == 0
n = n/primes[k]
factors << primes[k]  
redo  
end
end
 
# tests for special case: N is prime
 
if n == N
n = 1 
end
 
# tests for special case: N has one prime factor that is greater than the square root of N
 
if n > Math.sqrt(N).floor
factors << n
n = 1
end
 
end
 
# outputs prime factors
 
if factors.length > 0
puts "the prime factors of #{N} are"
puts factors.sort
else
puts "#{N} is prime"
end