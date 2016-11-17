$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'

#einfachrekursion
def fibo(n)
  check_pre(n.nat?)
  if n ==0
    0
  elsif n==1
    1
  elsif n>=2
    fibo(n-1)+ fibo(n-2)
  end
end

