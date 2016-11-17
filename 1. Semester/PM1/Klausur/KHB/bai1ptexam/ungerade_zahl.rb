class UngeradeZahl
  include Comperable, Enumerable
  attr_reader :zahl
  # Der sogenannte Konstruktur überpürft schon bei der Initialisierung
  # ob die gegebene Zahl eine Ungerade Zahl ist
  def initialize(value)
    raise ArgumentError, 'Kein Integer oder keine Ungerade Zahl!' if !value.integer? or value % 2 == 0
    @zahl = value
  end
  
  def to_s
     "#{self.class}[#{zahl}]"
  end
  
  def to_a
    [self.zahl]
  end  
  ‚
  def <=>(obj)
    return false if obj.nil? or !obj.is_a?(self.class)
    return true if self.equal(obj)
    return self.to_a <=> obj.to_a
  end
  
  def succ
    self.class.new(zahl+2)
  end
 
end