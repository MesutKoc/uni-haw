# Author:: Sascha Majewsky

class Max
  def max(array, &block)
    array.each do |x|
      yield x
   return array.last
    end
  end
end

test = Max.new
test.max [3,1,4,7]  do |n|
  sort = []
  sort << n
  sort.max
end
  

