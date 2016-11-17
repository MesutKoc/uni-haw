def fibonacci_v1(n)
  (n == 0 or n == 1) ? n : fibonacci(n-1) + fibonacci(n-2)
end

def fibonacci(n)
  fibonacci_(n-1, 1, 0)
end

def fibonacci_(n, accu, accu2)
  ( n <= 1? accu+accu2 : fibonacci_(n-1, accu+accu2, accu))
end

def fibonacci_it(number)
	acc = 1
	acc2 = 0
	while not(number <= 1)
    result = acc + acc2;
    acc = acc2;
    acc2 = result;
		number -= 1
	end
	acc + acc2
end