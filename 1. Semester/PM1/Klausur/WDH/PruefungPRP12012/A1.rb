include Enumerable

def ln(x,n)
  return false if x < 0 && n < 0
  sum = 0
  for i in 0 .. n
    sum += (x-1.0)**((2.0*i+1.0)) / ((2.0*i+1.0)*(x+1.0)**((2.0*i+1.0)))
  end
  sum*2
end

#sum+(1/((2.0*i-1)*(2.0*i+1)))

#puts ln(2,0).round(0.02)
#puts Math.log(2)
#assert_in_delta(Math.log(2),ln_iter(2,0),0.03)
#assert_in_delta(Math.log(2),ln_iter(2,1),0.002)

class Array
  def ue_7
    self.any? {|elem| elem.include?("end")}
  end
 
end

def groesste_wort(satz)
  new_arry = satz.split
  return new_arry.max {|x,y| x.length <=> y.length}
end

puts groesste_wort("hallo diggaaaaa")

h1 = { 1 => Array.new([1,4,7])}
  
def ue_7_b_10(zwei_dim_ary)
  if zwei_dim_ary.empty?()
    return true
  end

  all_size = zwei_dim_ary[0].size()

  return zwei_dim_ary.all?(){|elem| elem.size()==all_size}
end

def ue_7_b_11(drei_dim_ary)
  if drei_dim_ary.empty?()
    return true
  end

  all_size = drei_dim_ary[0].size()
  puts all_size

  return drei_dim_ary.all?(){|elem| elem.size()==all_size}
end

def ue_7_b_16(sam)
  return sam.map(){|x| [x,Math.sin(x)]}
end

def ue_7_b_12
  self.any? {|k,v| k[0] == k[1] and v == v}
end