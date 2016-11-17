class Queue
  def initialize()
    @new_queue= [1, 2, 3, 4]
  end

  def push(element)
    @new_queue << element
    @new_queue
  end

  def dequeue
    first_element = @new_queue[0]
    @new_queue.delete_at(0)
    first_element
  end

  def peek
    # first_element =
    @new_queue[0]
    #first_element
  end

  
  def to_s
    @new_queue.to_s
 
  end
  
  def test
    puts Queue.new().peek
  end 
end

test = Queue.new
#test.dequeue
test.test

#to do liste
#Method to_s
#Der ganze rest :----> 





