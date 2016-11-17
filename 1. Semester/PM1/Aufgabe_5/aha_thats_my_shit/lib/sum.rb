$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'





#einfachrekursion
def sum(n)
  check_pre(n.nat?)
  if n== 0
    n
  else n+sum(n-1)
  end
end

#endrekursion Sum
def sum(n)
  check_pre(n.nat?)
  sum_(0,n)
end
#delegation , rekursion
def sum_(accu, n)
  (n==0 ? accu : sum_(accu + n , n-1))
end

#iteration
def sum(n)
  check_pre(n.nat?)
  accu= 0
  while not(n==0)
    accu= accu + n
    n=  (n-1)
  end
  accu
end


#Iteration mit reduce
def sum(n)
#  check_pre(n.array?)
  (0..n).reduce (0){|accu,elem| accu= accu+elem}
end
