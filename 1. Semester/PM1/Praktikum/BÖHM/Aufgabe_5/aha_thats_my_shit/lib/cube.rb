$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'


#cube einfachrekursion
def cube(x,n)
  check_pre(x.nat?) and (n.nat?)
    if n == 0
       1
    elsif n== 1
       x
    else
       x * cube(x, n-1)
  
end
end

#cube endrekursion
def cube(x,n)
  cube_(1,x,n)
end
#delegation , rekursion
def cube_(accu,x, n)
  (n==0 ? accu : cube_(accu *n, x , (n-1)))
end

#cube iteration with while
def cube(x,n)
  check_pre(n.nat?) and (x.nat?)
  accu = 1 
   while not (n==0)
     accu = accu*x 
     n = n-1
   end
   accu
end

#iteration mit reduce
def cube(x,n)
  check_pre(x.nat?) and (n.nat?)
  n.reduce(x){|accu,elem|accu=accu**elem}
end
